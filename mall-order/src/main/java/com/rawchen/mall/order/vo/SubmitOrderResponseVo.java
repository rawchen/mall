package com.rawchen.mall.order.vo;

import com.rawchen.mall.order.entity.OrderEntity;
import lombok.Data;

/**
 * <p>Title: SubmitOrderResponseVo</p>
 * Description：
 * date：2022/2/1 22:50
 */
@Data
public class SubmitOrderResponseVo {

	private OrderEntity orderEntity;

	/**
	 * 错误状态码： 0----成功
	 */
	private Integer code;
}
