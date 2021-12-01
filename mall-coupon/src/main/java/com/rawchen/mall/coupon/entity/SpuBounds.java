package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品spu积分设置 [SpuBounds]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "SpuBounds", description = "商品spu积分设置对象")
public class SpuBounds extends DataEntity<SpuBounds> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	@ApiModelProperty(value = "spu_id")
	private Long spuId;

	/**
	 * 成长积分
	 */
	@ApiModelProperty(value = "成长积分")
	private java.lang.Float growBounds;

	/**
	 * 购物积分
	 */
	@ApiModelProperty(value = "购物积分")
	private java.lang.Float buyBounds;

	/**
	 * 优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]
	 */
	@ApiModelProperty(value = "优惠生效情况[1111（四个状态位，从右到左）;0 - 无优惠，成长积分是否赠送;1 - 无优惠，购物积分是否赠送;2 - 有优惠，成长积分是否赠送;3 - 有优惠，购物积分是否赠送【状态位0：不赠送，1：赠送】]")
	private Integer work;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setSpuId(Long spuId){
		this.spuId = spuId;
	}
	public Long getSpuId(){
		return spuId;
	}
	public void setGrowBounds(java.lang.Float growBounds){
		this.growBounds = growBounds;
	}
	public java.lang.Float getGrowBounds(){
		return growBounds;
	}
	public void setBuyBounds(java.lang.Float buyBounds){
		this.buyBounds = buyBounds;
	}
	public java.lang.Float getBuyBounds(){
		return buyBounds;
	}
	public void setWork(Integer work){
		this.work = work;
	}
	public Integer getWork(){
		return work;
	}
}