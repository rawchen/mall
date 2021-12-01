package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 首页轮播广告 [HomeAdv]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "HomeAdv", description = "首页轮播广告对象")
public class HomeAdv extends DataEntity<HomeAdv> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 名字
	 */
	@ApiModelProperty(value = "名字")
	private String name;

	/**
	 * 图片地址
	 */
	@ApiModelProperty(value = "图片地址")
	private String pic;

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
	 * 状态
	 */
	@ApiModelProperty(value = "状态")
	private Integer status;

	/**
	 * 点击数
	 */
	@ApiModelProperty(value = "点击数")
	private Integer clickCount;

	/**
	 * 广告详情连接地址
	 */
	@ApiModelProperty(value = "广告详情连接地址")
	private String url;

	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String note;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**
	 * 发布者
	 */
	@ApiModelProperty(value = "发布者")
	private Long publisherId;

	/**
	 * 审核者
	 */
	@ApiModelProperty(value = "审核者")
	private Long authId;

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
	public void setPic(String pic){
		this.pic = pic;
	}
	public String getPic(){
		return pic;
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
	public void setClickCount(Integer clickCount){
		this.clickCount = clickCount;
	}
	public Integer getClickCount(){
		return clickCount;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return url;
	}
	public void setNote(String note){
		this.note = note;
	}
	public String getNote(){
		return note;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return sort;
	}
	public void setPublisherId(Long publisherId){
		this.publisherId = publisherId;
	}
	public Long getPublisherId(){
		return publisherId;
	}
	public void setAuthId(Long authId){
		this.authId = authId;
	}
	public Long getAuthId(){
		return authId;
	}
}