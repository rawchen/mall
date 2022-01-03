package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * sku销售属性&值 [SkuSaleAttrValue]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "SkuSaleAttrValue", description = "sku销售属性&值对象")
public class SkuSaleAttrValue extends DataEntity<SkuSaleAttrValue> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * sku_id
	 */
	@ApiModelProperty(value = "sku_id")
	private Long skuId;

	/**
	 * attr_id
	 */
	@ApiModelProperty(value = "attr_id")
	private Long attrId;

	/**
	 * 销售属性名
	 */
	@ApiModelProperty(value = "销售属性名")
	private String attrName;

	/**
	 * 销售属性值
	 */
	@ApiModelProperty(value = "销售属性值")
	private String attrValue;

	/**
	 * 顺序
	 */
	@ApiModelProperty(value = "顺序")
	private Integer attrSort;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setSkuId(Long skuId){
		this.skuId = skuId;
	}
	public Long getSkuId(){
		return skuId;
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
}