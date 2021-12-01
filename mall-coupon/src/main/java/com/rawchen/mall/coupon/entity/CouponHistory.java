package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券领取历史记录 [CouponHistory]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "CouponHistory", description = "优惠券领取历史记录对象")
public class CouponHistory extends DataEntity<CouponHistory> implements Serializable {

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
	 * 会员id
	 */
	@ApiModelProperty(value = "会员id")
	private Long memberId;

	/**
	 * 会员名字
	 */
	@ApiModelProperty(value = "会员名字")
	private String memberNickName;

	/**
	 * 获取方式[0->后台赠送；1->主动领取]
	 */
	@ApiModelProperty(value = "获取方式[0->后台赠送；1->主动领取]")
	private Integer getType;

	/**
	 * 使用状态[0->未使用；1->已使用；2->已过期]
	 */
	@ApiModelProperty(value = "使用状态[0->未使用；1->已使用；2->已过期]")
	private Integer useType;

	/**
	 * 使用时间
	 */
	@ApiModelProperty(value = "使用时间")
	private Date useTime;

	/**
	 * 订单id
	 */
	@ApiModelProperty(value = "订单id")
	private Long orderId;

	/**
	 * 订单号
	 */
	@ApiModelProperty(value = "订单号")
	private Long orderSn;

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
	public void setMemberId(Long memberId){
		this.memberId = memberId;
	}
	public Long getMemberId(){
		return memberId;
	}
	public void setMemberNickName(String memberNickName){
		this.memberNickName = memberNickName;
	}
	public String getMemberNickName(){
		return memberNickName;
	}
	public void setGetType(Integer getType){
		this.getType = getType;
	}
	public Integer getGetType(){
		return getType;
	}
	public void setUseType(Integer useType){
		this.useType = useType;
	}
	public Integer getUseType(){
		return useType;
	}
	public void setUseTime(Date useTime){
		this.useTime = useTime;
	}
	public Date getUseTime(){
		return useTime;
	}
	public void setOrderId(Long orderId){
		this.orderId = orderId;
	}
	public Long getOrderId(){
		return orderId;
	}
	public void setOrderSn(Long orderSn){
		this.orderSn = orderSn;
	}
	public Long getOrderSn(){
		return orderSn;
	}
}