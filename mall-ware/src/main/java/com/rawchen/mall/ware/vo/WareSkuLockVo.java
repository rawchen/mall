package com.rawchen.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: WareSkuLockVo</p>
 * Description：
 * date：2022/2/2 11:13
 */
@Data
public class WareSkuLockVo {

	/**
	 * 订单号
	 */
	private String orderSn;

	/**
	 * 要锁住的所有库存信息
	 */
	private List<OrderItemVo> locks;

}
