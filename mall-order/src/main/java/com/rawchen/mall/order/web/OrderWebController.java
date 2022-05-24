package com.rawchen.mall.order.web;

import com.rawchen.common.exception.NotStockException;
import com.rawchen.mall.order.service.OrderService;
import com.rawchen.mall.order.utils.MailUtils;
import com.rawchen.mall.order.vo.OrderConfirmVo;
import com.rawchen.mall.order.vo.OrderSubmitVo;
import com.rawchen.mall.order.vo.SubmitOrderResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * <p>Title: OrderController</p>
 * Description：订单
 * date：2022/1/29 22:35
 */
@Controller
public class OrderWebController {

	@Autowired
	private OrderService orderService;

	@Autowired
	MailUtils mailUtils;

	@GetMapping("/toTrade")
	public String toTrade(Model model) throws ExecutionException, InterruptedException {
		OrderConfirmVo confirmVo = orderService.confirmOrder();

		model.addAttribute("orderConfirmData", confirmVo);
		return "confirm";
	}

	/**
	 * 下单功能
	 */
	@PostMapping("/submitOrder")
	public String submitOrder(OrderSubmitVo submitVo, Model model, RedirectAttributes redirectAttributes){

		try {
			SubmitOrderResponseVo responseVo = orderService.submitOrder(submitVo);
			// 下单失败回到订单重新确认订单信息
			if(responseVo.getCode() == 0){
				//邮件通知下单成功
				Map<String, Object> map = new HashMap<>();
				map.put("title", "下单成功");
				map.put("time", new Date());
				map.put("orderSn", responseVo.getOrderEntity().getOrderSn());
				map.put("memberUsername", responseVo.getOrderEntity().getMemberUsername());
				map.put("totalAmount", responseVo.getOrderEntity().getTotalAmount());
				map.put("payAmount", responseVo.getOrderEntity().getPayAmount());
				map.put("freightAmount", responseVo.getOrderEntity().getFreightAmount());
				map.put("receiverDetailAddress", responseVo.getOrderEntity().getReceiverProvince() +
						responseVo.getOrderEntity().getReceiverCity() +
						responseVo.getOrderEntity().getReceiverRegion() +
						responseVo.getOrderEntity().getReceiverDetailAddress());
				map.put("content", "订单已经生成，请尽快支付。（订单有效时间15分钟）");
				map.put("url", "http://member.mall.com/memberOrder.html");
				String subject = "你有新的商城订单待付款。";
				mailUtils.sendHtmlTemplateMail(map, "2221999792@qq.com", subject, "owner.html");

				// 下单成功取支付选项
				model.addAttribute("submitOrderResp", responseVo);
				return "pay";
			}else{
				String msg = "下单失败";
				switch (responseVo.getCode()){
					case 1: msg += "订单信息过期,请刷新在提交";break;
					case 2: msg += "订单商品价格发送变化,请确认后再次提交";break;
					case 3: msg += "商品库存不足";break;
				}
				redirectAttributes.addFlashAttribute("msg", msg);
				return "redirect:http://order.mall.com/toTrade";
			}
		} catch (Exception e) {
			if (e instanceof NotStockException){
				String message = e.getMessage();
				redirectAttributes.addFlashAttribute("msg", message);
			}
			return "redirect:http://order.mall.com/toTrade";
		}
	}
}
