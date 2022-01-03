package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * spu信息 [SpuInfo]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "SpuInfo", description = "spu信息对象")
public class SpuInfo extends DataEntity<SpuInfo> implements Serializable {

	/**
	 * 商品id
	 */
	@ApiModelProperty(value = "商品id")
	private Long id;

	/**
	 * 商品名称
	 */
	@ApiModelProperty(value = "商品名称")
	private String spuName;

	/**
	 * 商品描述
	 */
	@ApiModelProperty(value = "商品描述")
	private String spuDescription;

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
	 * 上架状态[0 - 下架，1 - 上架]
	 */
	@ApiModelProperty(value = "上架状态[0 - 下架，1 - 上架]")
	private Integer publishStatus;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setSpuName(String spuName){
		this.spuName = spuName;
	}
	public String getSpuName(){
		return spuName;
	}
	public void setSpuDescription(String spuDescription){
		this.spuDescription = spuDescription;
	}
	public String getSpuDescription(){
		return spuDescription;
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
	public void setPublishStatus(Integer publishStatus){
		this.publishStatus = publishStatus;
	}
	public Integer getPublishStatus(){
		return publishStatus;
	}
}