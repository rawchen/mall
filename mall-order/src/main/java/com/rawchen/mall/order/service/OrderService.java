package com.rawchen.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.to.mq.SecKillOrderTo;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.order.entity.OrderEntity;
import com.rawchen.mall.order.vo.OrderConfirmVo;
import com.rawchen.mall.order.vo.OrderSubmitVo;
import com.rawchen.mall.order.vo.PayAsyncVo;
import com.rawchen.mall.order.vo.PayVo;
import com.rawchen.mall.order.vo.SubmitOrderResponseVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:54:56
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

	/**
	 * 给订单确认页返回需要的数据
	 */
	OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

	/**
	 * 下单操作
	 */
	SubmitOrderResponseVo submitOrder(OrderSubmitVo submitVo);

	OrderEntity getOrderByOrderSn(String orderSn);

	void closeOrder(OrderEntity entity);

	/**
	 * 获取当前订单的支付信息
	 */
	PayVo getOrderPay(String orderSn);

	PageUtils queryPageWithItem(@Param("params") Map<String, Object> params);

	/**
	 * 处理支付宝的返回数据
	 */
	String handlePayResult(PayAsyncVo vo);

	void createSecKillOrder(SecKillOrderTo secKillOrderTo);
}

