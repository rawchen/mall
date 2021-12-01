package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 优惠券分类关联 [CouponSpuCategoryRelation]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "CouponSpuCategoryRelation", description = "优惠券分类关联对象")
public class CouponSpuCategoryRelation extends DataEntity<CouponSpuCategoryRelation> implements Serializable {

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
	 * 产品分类id
	 */
	@ApiModelProperty(value = "产品分类id")
	private Long categoryId;

	/**
	 * 产品分类名称
	 */
	@ApiModelProperty(value = "产品分类名称")
	private String categoryName;

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
	public void setCategoryId(Long categoryId){
		this.categoryId = categoryId;
	}
	public Long getCategoryId(){
		return categoryId;
	}
	public void setCategoryName(String categoryName){
		this.categoryName = categoryName;
	}
	public String getCategoryName(){
		return categoryName;
	}
}