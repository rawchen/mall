package com.rawchen.mall.order.controller;

import com.rawchen.common.utils.PageUtils;
import com.rawchen.common.utils.R;
import com.rawchen.mall.order.entity.PaymentInfoEntity;
import com.rawchen.mall.order.service.PaymentInfoService;
import com.rawchen.mall.order.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;


/**
 * 支付信息表
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:54:56
 */
@RestController
@RequestMapping("order/paymentinfo")
public class PaymentInfoController {
    @Autowired
    private PaymentInfoService paymentInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("order:paymentinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paymentInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("order:paymentinfo:info")
    public R info(@PathVariable("id") Long id){
		PaymentInfoEntity paymentInfo = paymentInfoService.getById(id);

        return R.ok().put("paymentInfo", paymentInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("order:paymentinfo:save")
    public R save(@RequestBody PaymentInfoEntity paymentInfo){
		paymentInfoService.save(paymentInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("order:paymentinfo:update")
    public R update(@RequestBody PaymentInfoEntity paymentInfo){
		paymentInfoService.updateById(paymentInfo);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:paymentinfo:delete")
    public R delete(@RequestBody Long[] ids){
		paymentInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response) {
        List<PaymentInfoEntity> list = paymentInfoService.list();

        Map<String, Object> map = new HashMap();

        String[] headers = {"ID", "订单ID", "订单号", "支付宝交易流水号", "支付总金额", "交易内容", "支付状态", "创建时间", "确认时间", "回调内容", "回调时间"};
        String[] exportFields = {"id", "orderId", "orderSn", "alipayTradeNo", "totalAmount", "subject", "paymentStatus", "createTime", "confirmTime", "callbackContent", "callbackTime"};
        String fileName = "支付流水报表";
        map.put("headers", headers);
        map.put("dataList", list);
        map.put("fileName", fileName);
        map.put("exportFields", exportFields);
        List<Map> mapList = new ArrayList();
        mapList.add(map);

        ExcelUtil.exportMultisheetExcelForWidthAdaptive("excel-" + System.currentTimeMillis(), mapList, response);

    }

}
