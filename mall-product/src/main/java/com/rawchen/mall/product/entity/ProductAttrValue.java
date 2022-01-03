package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * spu属性值 [ProductAttrValue]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "ProductAttrValue", description = "spu属性值对象")
public class ProductAttrValue extends DataEntity<ProductAttrValue> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 商品id
	 */
	@ApiModelProperty(value = "商品id")
	private Long spuId;

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
	 * 属性值
	 */
	@ApiModelProperty(value = "属性值")
	private String attrValue;

	/**
	 * 顺序
	 */
	@ApiModelProperty(value = "顺序")
	private Integer attrSort;

	/**
	 * 快速展示【是否展示在介绍上；0-否 1-是】
	 */
	@ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】")
	private Integer quickShow;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setSpuId(Long spuId){
		this.spuId = spuId;
	}
	public Long getSpuId(){
		return spuId;
	}
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
	public void setAttrValue(String attrValue){
		this.attrValue = attrValue;
	}
	public String getAttrValue(){
		return attrValue;
	}
	public void setAttrSort(Integer attrSort){
		this.attrSort = attrSort;
	}
	public Integer getAttrSort(){
		return attrSort;
	}
	public void setQuickShow(Integer quickShow){
		this.quickShow = quickShow;
	}
	public Integer getQuickShow(){
		return quickShow;
	}
}