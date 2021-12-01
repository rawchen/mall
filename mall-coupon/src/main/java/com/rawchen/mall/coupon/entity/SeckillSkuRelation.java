package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 秒杀活动商品关联 [SeckillSkuRelation]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "SeckillSkuRelation", description = "秒杀活动商品关联对象")
public class SeckillSkuRelation extends DataEntity<SeckillSkuRelation> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 活动id
	 */
	@ApiModelProperty(value = "活动id")
	private Long promotionId;

	/**
	 * 活动场次id
	 */
	@ApiModelProperty(value = "活动场次id")
	private Long promotionSessionId;

	/**
	 * 商品id
	 */
	@ApiModelProperty(value = "商品id")
	private Long skuId;

	/**
	 * 秒杀价格
	 */
	@ApiModelProperty(value = "秒杀价格")
	private java.lang.Float seckillPrice;

	/**
	 * 秒杀总量
	 */
	@ApiModelProperty(value = "秒杀总量")
	private java.lang.Float seckillCount;

	/**
	 * 每人限购数量
	 */
	@ApiModelProperty(value = "每人限购数量")
	private java.lang.Float seckillLimit;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer seckillSort;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setPromotionId(Long promotionId){
		this.promotionId = promotionId;
	}
	public Long getPromotionId(){
		return promotionId;
	}
	public void setPromotionSessionId(Long promotionSessionId){
		this.promotionSessionId = promotionSessionId;
	}
	public Long getPromotionSessionId(){
		return promotionSessionId;
	}
	public void setSkuId(Long skuId){
		this.skuId = skuId;
	}
	public Long getSkuId(){
		return skuId;
	}
	public void setSeckillPrice(java.lang.Float seckillPrice){
		this.seckillPrice = seckillPrice;
	}
	public java.lang.Float getSeckillPrice(){
		return seckillPrice;
	}
	public void setSeckillCount(java.lang.Float seckillCount){
		this.seckillCount = seckillCount;
	}
	public java.lang.Float getSeckillCount(){
		return seckillCount;
	}
	public void setSeckillLimit(java.lang.Float seckillLimit){
		this.seckillLimit = seckillLimit;
	}
	public java.lang.Float getSeckillLimit(){
		return seckillLimit;
	}
	public void setSeckillSort(Integer seckillSort){
		this.seckillSort = seckillSort;
	}
	public Integer getSeckillSort(){
		return seckillSort;
	}
}