package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀活动 [SeckillPromotion]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "SeckillPromotion", description = "秒杀活动对象")
public class SeckillPromotion extends DataEntity<SeckillPromotion> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 活动标题
	 */
	@ApiModelProperty(value = "活动标题")
	private String title;

	/**
	 * 开始日期
	 */
	@ApiModelProperty(value = "开始日期")
	private Date startTime;

	/**
	 * 结束日期
	 */
	@ApiModelProperty(value = "结束日期")
	private Date endTime;

	/**
	 * 上下线状态
	 */
	@ApiModelProperty(value = "上下线状态")
	private Integer status;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private Long userId;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
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
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public Long getUserId(){
		return userId;
	}
}