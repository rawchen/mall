package com.rawchen.mall.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>Title: FareVo</p>
 * Description：
 * date：2022/2/2 0:05
 */
@Data
public class FareVo {
	private MemberAddressVo memberAddressVo;

	private BigDecimal fare;
}
