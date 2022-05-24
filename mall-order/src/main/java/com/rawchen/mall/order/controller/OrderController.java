package com.rawchen.mall.order.controller;

import com.rawchen.common.utils.PageUtils;
import com.rawchen.common.utils.R;
import com.rawchen.mall.order.entity.OrderEntity;
import com.rawchen.mall.order.service.OrderService;
import com.rawchen.mall.order.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 订单
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:54:56
 */
@RestController
@RequestMapping("order/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

	/**
	 * 查询当前登录的用户的所有订单信息
	 */
	@PostMapping("/listWithItem")
	public R listWithItem(@RequestBody Map<String, Object> params){
		PageUtils page = orderService.queryPageWithItem(params);

		return R.ok().put("page", page);
	}

    @GetMapping("/status/{orderSn}")
    public R getOrderStatus(@PathVariable("orderSn") String orderSn){
    	OrderEntity orderEntity = orderService.getOrderByOrderSn(orderSn);

    	return R.ok().setData(orderEntity);
	}

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:order:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:order:info")
    public R info(@PathVariable("id") Long id){
		OrderEntity order = orderService.getById(id);

        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:order:save")
    public R save(@RequestBody OrderEntity order){
		orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:order:update")
    public R update(@RequestBody OrderEntity order){
		orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:order:delete")
    public R delete(@RequestBody Long[] ids){
		orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletResponse response) {
		List<OrderEntity> list = orderService.list();

		Map<String, Object> map = new HashMap();

		String[] headers = {"ID", "会员ID", "订单号", "使用的优惠券", "创建日期", "用户名", "订单总额", "应付总额", "运费金额", "促销优化金额（促销价、满减、阶梯价）", "积分抵扣金额", "优惠券抵扣金额", "后台调整折扣", "支付方式", "订单来源", "订单状态", "收货人姓名", "收货人电话", "省份/直辖市", "城市", "区", "详细地址", "支付时间"};
		String[] exportFields = {"id", "memberId", "orderSn", "couponId", "createTime", "memberUsername", "totalAmount", "payAmount", "freightAmount", "promotionAmount", "integrationAmount", "couponAmount", "discountAmount", "payType", "sourceType", "status", "receiverName", "receiverPhone", "receiverProvince", "receiverCity", "receiverRegion", "receiverDetailAddress", "paymentTime"};
		String fileName = "订单导出表";
		map.put("headers", headers);
		map.put("dataList", list);
		map.put("fileName", fileName);
		map.put("exportFields", exportFields);
		List<Map> mapList = new ArrayList();
		mapList.add(map);

		ExcelUtil.exportMultisheetExcelForWidthAdaptive("excel-" + System.currentTimeMillis(), mapList, response);

	}

}
