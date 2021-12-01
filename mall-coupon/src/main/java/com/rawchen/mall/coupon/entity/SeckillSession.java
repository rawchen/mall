package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀活动场次 [SeckillSession]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "SeckillSession", description = "秒杀活动场次对象")
public class SeckillSession extends DataEntity<SeckillSession> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 场次名称
	 */
	@ApiModelProperty(value = "场次名称")
	private String name;

	/**
	 * 每日开始时间
	 */
	@ApiModelProperty(value = "每日开始时间")
	private Date startTime;

	/**
	 * 每日结束时间
	 */
	@ApiModelProperty(value = "每日结束时间")
	private Date endTime;

	/**
	 * 启用状态
	 */
	@ApiModelProperty(value = "启用状态")
	private Integer status;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
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
}