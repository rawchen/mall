package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * sku信息 [SkuInfo]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "SkuInfo", description = "sku信息对象")
public class SkuInfo extends DataEntity<SkuInfo> implements Serializable {

	/**
	 * skuid
	 */
	@ApiModelProperty(value = "skuid")
	private Long skuId;

	/**
	 * spuid
	 */
	@ApiModelProperty(value = "spuid")
	private Long spuId;

	@ApiModelProperty(value = "sku_code")
	private String skuCode;

	/**
	 * sku名称
	 */
	@ApiModelProperty(value = "sku名称")
	private String skuName;

	/**
	 * sku介绍描述
	 */
	@ApiModelProperty(value = "sku介绍描述")
	private String skuDesc;

	/**
	 * 所属分类id
	 */
	@ApiModelProperty(value = "所属分类id")
	private Long catalogId;

	/**
	 * 品牌id
	 */
	@ApiModelProperty(value = "品牌id")
	private Long brandId;

	/**
	 * 默认图片
	 */
	@ApiModelProperty(value = "默认图片")
	private String skuDefaultImg;

	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题")
	private String skuTitle;

	/**
	 * 副标题
	 */
	@ApiModelProperty(value = "副标题")
	private String skuSubtitle;

	/**
	 * 价格
	 */
	@ApiModelProperty(value = "价格")
	private java.lang.Float price;

	/**
	 * 重量
	 */
	@ApiModelProperty(value = "重量")
	private java.lang.Float weight;

	public void setSkuId(Long skuId){
		this.skuId = skuId;
	}
	public Long getSkuId(){
		return skuId;
	}
	public void setSpuId(Long spuId){
		this.spuId = spuId;
	}
	public Long getSpuId(){
		return spuId;
	}
	public void setSkuCode(String skuCode){
		this.skuCode = skuCode;
	}
	public String getSkuCode(){
		return skuCode;
	}
	public void setSkuName(String skuName){
		this.skuName = skuName;
	}
	public String getSkuName(){
		return skuName;
	}
	public void setSkuDesc(String skuDesc){
		this.skuDesc = skuDesc;
	}
	public String getSkuDesc(){
		return skuDesc;
	}
	public void setCatalogId(Long catalogId){
		this.catalogId = catalogId;
	}
	public Long getCatalogId(){
		return catalogId;
	}
	public void setBrandId(Long brandId){
		this.brandId = brandId;
	}
	public Long getBrandId(){
		return brandId;
	}
	public void setSkuDefaultImg(String skuDefaultImg){
		this.skuDefaultImg = skuDefaultImg;
	}
	public String getSkuDefaultImg(){
		return skuDefaultImg;
	}
	public void setSkuTitle(String skuTitle){
		this.skuTitle = skuTitle;
	}
	public String getSkuTitle(){
		return skuTitle;
	}
	public void setSkuSubtitle(String skuSubtitle){
		this.skuSubtitle = skuSubtitle;
	}
	public String getSkuSubtitle(){
		return skuSubtitle;
	}
	public void setPrice(java.lang.Float price){
		this.price = price;
	}
	public java.lang.Float getPrice(){
		return price;
	}
	public void setWeight(java.lang.Float weight){
		this.weight = weight;
	}
	public java.lang.Float getWeight(){
		return weight;
	}
}