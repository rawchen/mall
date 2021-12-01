package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 优惠券与产品关联 [CouponSpuRelation]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "CouponSpuRelation", description = "优惠券与产品关联对象")
public class CouponSpuRelation extends DataEntity<CouponSpuRelation> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 优惠券id
	 */
	@ApiModelProperty(value = "优惠券id")
	private Long couponId;

	/**
	 * spu_id
	 */
	@ApiModelProperty(value = "spu_id")
	private Long spuId;

	/**
	 * spu_name
	 */
	@ApiModelProperty(value = "spu_name")
	private String spuName;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setCouponId(Long couponId){
		this.couponId = couponId;
	}
	public Long getCouponId(){
		return couponId;
	}
	public void setSpuId(Long spuId){
		this.spuId = spuId;
	}
	public Long getSpuId(){
		return spuId;
	}
	public void setSpuName(String spuName){
		this.spuName = spuName;
	}
	public String getSpuName(){
		return spuName;
	}
}