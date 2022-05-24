package com.rawchen.mall.product.vo;

import lombok.Data;

import java.math.BigDecimal;
/**
 * <p>Title: AttrRespVo</p>
 * Description：会员价格
 * date：2022/1/2 19:56
 */
@Data
public class MemberPrice {

    private Long id;

    private String name;

    private BigDecimal price;
}