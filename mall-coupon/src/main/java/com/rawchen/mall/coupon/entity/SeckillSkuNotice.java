package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品通知订阅 [SeckillSkuNotice]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "SeckillSkuNotice", description = "秒杀商品通知订阅对象")
public class SeckillSkuNotice extends DataEntity<SeckillSkuNotice> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * member_id
	 */
	@ApiModelProperty(value = "member_id")
	private Long memberId;

	/**
	 * sku_id
	 */
	@ApiModelProperty(value = "sku_id")
	private Long skuId;

	/**
	 * 活动场次id
	 */
	@ApiModelProperty(value = "活动场次id")
	private Long sessionId;

	/**
	 * 订阅时间
	 */
	@ApiModelProperty(value = "订阅时间")
	private Date subcribeTime;

	/**
	 * 发送时间
	 */
	@ApiModelProperty(value = "发送时间")
	private Date sendTime;

	/**
	 * 通知方式[0-短信，1-邮件]
	 */
	@ApiModelProperty(value = "通知方式[0-短信，1-邮件]")
	private Integer noticeType;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setMemberId(Long memberId){
		this.memberId = memberId;
	}
	public Long getMemberId(){
		return memberId;
	}
	public void setSkuId(Long skuId){
		this.skuId = skuId;
	}
	public Long getSkuId(){
		return skuId;
	}
	public void setSessionId(Long sessionId){
		this.sessionId = sessionId;
	}
	public Long getSessionId(){
		return sessionId;
	}
	public void setSubcribeTime(Date subcribeTime){
		this.subcribeTime = subcribeTime;
	}
	public Date getSubcribeTime(){
		return subcribeTime;
	}
	public void setSendTime(Date sendTime){
		this.sendTime = sendTime;
	}
	public Date getSendTime(){
		return sendTime;
	}
	public void setNoticeType(Integer noticeType){
		this.noticeType = noticeType;
	}
	public Integer getNoticeType(){
		return noticeType;
	}
}