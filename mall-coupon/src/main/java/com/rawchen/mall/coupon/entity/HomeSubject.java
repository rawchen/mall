package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 首页专题表 [HomeSubject]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "HomeSubject", description = "首页专题表对象")
public class HomeSubject extends DataEntity<HomeSubject> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 专题名字
	 */
	@ApiModelProperty(value = "专题名字")
	private String name;

	/**
	 * 专题标题
	 */
	@ApiModelProperty(value = "专题标题")
	private String title;

	/**
	 * 专题副标题
	 */
	@ApiModelProperty(value = "专题副标题")
	private String subTitle;

	/**
	 * 显示状态
	 */
	@ApiModelProperty(value = "显示状态")
	private Integer status;

	/**
	 * 详情连接
	 */
	@ApiModelProperty(value = "详情连接")
	private String url;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**
	 * 专题图片地址
	 */
	@ApiModelProperty(value = "专题图片地址")
	private String img;

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
	public void setTitle(String title){
		this.title = title;
	}
	public String getTitle(){
		return title;
	}
	public void setSubTitle(String subTitle){
		this.subTitle = subTitle;
	}
	public String getSubTitle(){
		return subTitle;
	}
	public void setStatus(Integer status){
		this.status = status;
	}
	public Integer getStatus(){
		return status;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public String getUrl(){
		return url;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return sort;
	}
	public void setImg(String img){
		this.img = img;
	}
	public String getImg(){
		return img;
	}
}