package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券信息 [Coupon]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "Coupon", description = "优惠券信息对象")
public class Coupon extends DataEntity<Coupon> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
	 */
	@ApiModelProperty(value = "优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]")
	private Integer couponType;

	/**
	 * 优惠券图片
	 */
	@ApiModelProperty(value = "优惠券图片")
	private String couponImg;

	/**
	 * 优惠卷名字
	 */
	@ApiModelProperty(value = "优惠卷名字")
	private String couponName;

	/**
	 * 数量
	 */
	@ApiModelProperty(value = "数量")
	private Integer num;

	/**
	 * 金额
	 */
	@ApiModelProperty(value = "金额")
	private java.lang.Float amount;

	/**
	 * 每人限领张数
	 */
	@ApiModelProperty(value = "每人限领张数")
	private Integer perLimit;

	/**
	 * 使用门槛
	 */
	@ApiModelProperty(value = "使用门槛")
	private java.lang.Float minPoint;

	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间")
	private Date startTime;

	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间")
	private Date endTime;

	/**
	 * 使用类型[0->全场通用；1->指定分类；2->指定商品]
	 */
	@ApiModelProperty(value = "使用类型[0->全场通用；1->指定分类；2->指定商品]")
	private Integer useType;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String note;

	/**
	 * 发行数量
	 */
	@ApiModelProperty(value = "发行数量")
	private Integer publishCount;

	/**
	 * 已使用数量
	 */
	@ApiModelProperty(value = "已使用数量")
	private Integer useCount;

	/**
	 * 领取数量
	 */
	@ApiModelProperty(value = "领取数量")
	private Integer receiveCount;

	/**
	 * 可以领取的开始日期
	 */
	@ApiModelProperty(value = "可以领取的开始日期")
	private Date enableStartTime;

	/**
	 * 可以领取的结束日期
	 */
	@ApiModelProperty(value = "可以领取的结束日期")
	private Date enableEndTime;

	/**
	 * 优惠码
	 */
	@ApiModelProperty(value = "优惠码")
	private String code;

	/**
	 * 可以领取的会员等级[0->不限等级，其他-对应等级]
	 */
	@ApiModelProperty(value = "可以领取的会员等级[0->不限等级，其他-对应等级]")
	private Integer memberLevel;

	/**
	 * 发布状态[0-未发布，1-已发布]
	 */
	@ApiModelProperty(value = "发布状态[0-未发布，1-已发布]")
	private Integer publish;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setCouponType(Integer couponType){
		this.couponType = couponType;
	}
	public Integer getCouponType(){
		return couponType;
	}
	public void setCouponImg(String couponImg){
		this.couponImg = couponImg;
	}
	public String getCouponImg(){
		return couponImg;
	}
	public void setCouponName(String couponName){
		this.couponName = couponName;
	}
	public String getCouponName(){
		return couponName;
	}
	public void setNum(Integer num){
		this.num = num;
	}
	public Integer getNum(){
		return num;
	}
	public void setAmount(java.lang.Float amount){
		this.amount = amount;
	}
	public java.lang.Float getAmount(){
		return amount;
	}
	public void setPerLimit(Integer perLimit){
		this.perLimit = perLimit;
	}
	public Integer getPerLimit(){
		return perLimit;
	}
	public void setMinPoint(java.lang.Float minPoint){
		this.minPoint = minPoint;
	}
	public java.lang.Float getMinPoint(){
		return minPoint;
	}
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	public Date getStartTime(){
		return startTime;
	}
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	public Date getEndTime(){
		return endTime;
	}
	public void setUseType(Integer useType){
		this.useType = useType;
	}
	public Integer getUseType(){
		return useType;
	}
	public void setNote(String note){
		this.note = note;
	}
	public String getNote(){
		return note;
	}
	public void setPublishCount(Integer publishCount){
		this.publishCount = publishCount;
	}
	public Integer getPublishCount(){
		return publishCount;
	}
	public void setUseCount(Integer useCount){
		this.useCount = useCount;
	}
	public Integer getUseCount(){
		return useCount;
	}
	public void setReceiveCount(Integer receiveCount){
		this.receiveCount = receiveCount;
	}
	public Integer getReceiveCount(){
		return receiveCount;
	}
	public void setEnableStartTime(Date enableStartTime){
		this.enableStartTime = enableStartTime;
	}
	public Date getEnableStartTime(){
		return enableStartTime;
	}
	public void setEnableEndTime(Date enableEndTime){
		this.enableEndTime = enableEndTime;
	}
	public Date getEnableEndTime(){
		return enableEndTime;
	}
	public void setCode(String code){
		this.code = code;
	}
	public String getCode(){
		return code;
	}
	public void setMemberLevel(Integer memberLevel){
		this.memberLevel = memberLevel;
	}
	public Integer getMemberLevel(){
		return memberLevel;
	}
	public void setPublish(Integer publish){
		this.publish = publish;
	}
	public Integer getPublish(){
		return publish;
	}
}