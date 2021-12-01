package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品会员价格 [MemberPrice]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "MemberPrice", description = "商品会员价格对象")
public class MemberPrice extends DataEntity<MemberPrice> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * sku_id
	 */
	@ApiModelProperty(value = "sku_id")
	private Long skuId;

	/**
	 * 会员等级id
	 */
	@ApiModelProperty(value = "会员等级id")
	private Long memberLevelId;

	/**
	 * 会员等级名
	 */
	@ApiModelProperty(value = "会员等级名")
	private String memberLevelName;

	/**
	 * 会员对应价格
	 */
	@ApiModelProperty(value = "会员对应价格")
	private java.lang.Float memberPrice;

	/**
	 * 可否叠加其他优惠[0-不可叠加优惠，1-可叠加]
	 */
	@ApiModelProperty(value = "可否叠加其他优惠[0-不可叠加优惠，1-可叠加]")
	private Integer addOther;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setSkuId(Long skuId){
		this.skuId = skuId;
	}
	public Long getSkuId(){
		return skuId;
	}
	public void setMemberLevelId(Long memberLevelId){
		this.memberLevelId = memberLevelId;
	}
	public Long getMemberLevelId(){
		return memberLevelId;
	}
	public void setMemberLevelName(String memberLevelName){
		this.memberLevelName = memberLevelName;
	}
	public String getMemberLevelName(){
		return memberLevelName;
	}
	public void setMemberPrice(java.lang.Float memberPrice){
		this.memberPrice = memberPrice;
	}
	public java.lang.Float getMemberPrice(){
		return memberPrice;
	}
	public void setAddOther(Integer addOther){
		this.addOther = addOther;
	}
	public Integer getAddOther(){
		return addOther;
	}
}