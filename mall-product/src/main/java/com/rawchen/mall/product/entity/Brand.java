package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 品牌 [Brand]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "Brand", description = "品牌对象")
public class Brand extends DataEntity<Brand> implements Serializable {

	/**
	 * 品牌id
	 */
	@ApiModelProperty(value = "品牌id")
	private Long brandId;

	/**
	 * 品牌名
	 */
	@ApiModelProperty(value = "品牌名")
	private String name;

	/**
	 * 品牌logo地址
	 */
	@ApiModelProperty(value = "品牌logo地址")
	private String logo;

	/**
	 * 介绍
	 */
	@ApiModelProperty(value = "介绍")
	private String descript;

	/**
	 * 显示状态[0-不显示；1-显示]
	 */
	@ApiModelProperty(value = "显示状态[0-不显示；1-显示]")
	private Integer showStatus;

	/**
	 * 检索首字母
	 */
	@ApiModelProperty(value = "检索首字母")
	private String firstLetter;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	public void setBrandId(Long brandId){
		this.brandId = brandId;
	}
	public Long getBrandId(){
		return brandId;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setLogo(String logo){
		this.logo = logo;
	}
	public String getLogo(){
		return logo;
	}
	public void setDescript(String descript){
		this.descript = descript;
	}
	public String getDescript(){
		return descript;
	}
	public void setShowStatus(Integer showStatus){
		this.showStatus = showStatus;
	}
	public Integer getShowStatus(){
		return showStatus;
	}
	public void setFirstLetter(String firstLetter){
		this.firstLetter = firstLetter;
	}
	public String getFirstLetter(){
		return firstLetter;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return sort;
	}
}