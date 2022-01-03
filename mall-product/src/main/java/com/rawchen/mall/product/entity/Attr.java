package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品属性 [Attr]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "Attr", description = "商品属性对象")
public class Attr extends DataEntity<Attr> implements Serializable {

	/**
	 * 属性id
	 */
	@ApiModelProperty(value = "属性id")
	private Long attrId;

	/**
	 * 属性名
	 */
	@ApiModelProperty(value = "属性名")
	private String attrName;

	/**
	 * 是否需要检索[0-不需要，1-需要]
	 */
	@ApiModelProperty(value = "是否需要检索[0-不需要，1-需要]")
	private Integer searchType;

	/**
	 * 值类型[0-为单个值，1-可以选择多个值]
	 */
	@ApiModelProperty(value = "值类型[0-为单个值，1-可以选择多个值]")
	private Integer valueType;

	/**
	 * 属性图标
	 */
	@ApiModelProperty(value = "属性图标")
	private String icon;

	/**
	 * 可选值列表[用逗号分隔]
	 */
	@ApiModelProperty(value = "可选值列表[用逗号分隔]")
	private String valueSelect;

	/**
	 * 属性类型[0-销售属性，1-基本属性]
	 */
	@ApiModelProperty(value = "属性类型[0-销售属性，1-基本属性]")
	private Integer attrType;

	/**
	 * 启用状态[0 - 禁用，1 - 启用]
	 */
	@ApiModelProperty(value = "启用状态[0 - 禁用，1 - 启用]")
	private Long enable;

	/**
	 * 所属分类
	 */
	@ApiModelProperty(value = "所属分类")
	private Long catelogId;

	/**
	 * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
	 */
	@ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
	private Integer showDesc;

	public void setAttrId(Long attrId){
		this.attrId = attrId;
	}
	public Long getAttrId(){
		return attrId;
	}
	public void setAttrName(String attrName){
		this.attrName = attrName;
	}
	public String getAttrName(){
		return attrName;
	}
	public void setSearchType(Integer searchType){
		this.searchType = searchType;
	}
	public Integer getSearchType(){
		return searchType;
	}
	public void setValueType(Integer valueType){
		this.valueType = valueType;
	}
	public Integer getValueType(){
		return valueType;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getIcon(){
		return icon;
	}
	public void setValueSelect(String valueSelect){
		this.valueSelect = valueSelect;
	}
	public String getValueSelect(){
		return valueSelect;
	}
	public void setAttrType(Integer attrType){
		this.attrType = attrType;
	}
	public Integer getAttrType(){
		return attrType;
	}
	public void setEnable(Long enable){
		this.enable = enable;
	}
	public Long getEnable(){
		return enable;
	}
	public void setCatelogId(Long catelogId){
		this.catelogId = catelogId;
	}
	public Long getCatelogId(){
		return catelogId;
	}
	public void setShowDesc(Integer showDesc){
		this.showDesc = showDesc;
	}
	public Integer getShowDesc(){
		return showDesc;
	}
}