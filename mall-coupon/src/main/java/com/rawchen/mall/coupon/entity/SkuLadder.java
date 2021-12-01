package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品阶梯价格 [SkuLadder]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "SkuLadder", description = "商品阶梯价格对象")
public class SkuLadder extends DataEntity<SkuLadder> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * spu_id
	 */
	@ApiModelProperty(value = "spu_id")
	private Long skuId;

	/**
	 * 满几件
	 */
	@ApiModelProperty(value = "满几件")
	private Integer fullCount;

	/**
	 * 打几折
	 */
	@ApiModelProperty(value = "打几折")
	private java.lang.Float discount;

	/**
	 * 折后价
	 */
	@ApiModelProperty(value = "折后价")
	private java.lang.Float price;

	/**
	 * 是否叠加其他优惠[0-不可叠加，1-可叠加]
	 */
	@ApiModelProperty(value = "是否叠加其他优惠[0-不可叠加，1-可叠加]")
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
	public void setFullCount(Integer fullCount){
		this.fullCount = fullCount;
	}
	public Integer getFullCount(){
		return fullCount;
	}
	public void setDiscount(java.lang.Float discount){
		this.discount = discount;
	}
	public java.lang.Float getDiscount(){
		return discount;
	}
	public void setPrice(java.lang.Float price){
		this.price = price;
	}
	public java.lang.Float getPrice(){
		return price;
	}
	public void setAddOther(Integer addOther){
		this.addOther = addOther;
	}
	public Integer getAddOther(){
		return addOther;
	}
}