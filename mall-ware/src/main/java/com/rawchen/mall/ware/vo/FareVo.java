package com.rawchen.mall.ware.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>Title: FareVo</p>
 * Description：
 * date：2022/2/1 20:46
 */
@Data
public class FareVo {

	private MemberAddressVo memberAddressVo;

	private BigDecimal fare;
}
