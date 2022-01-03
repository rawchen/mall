package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * sku图片 [SkuImages]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "SkuImages", description = "sku图片对象")
public class SkuImages extends DataEntity<SkuImages> implements Serializable {

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
	 * 图片地址
	 */
	@ApiModelProperty(value = "图片地址")
	private String imgUrl;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer imgSort;

	/**
	 * 默认图[0 - 不是默认图，1 - 是默认图]
	 */
	@ApiModelProperty(value = "默认图[0 - 不是默认图，1 - 是默认图]")
	private Integer defaultImg;

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
	public void setImgUrl(String imgUrl){
		this.imgUrl = imgUrl;
	}
	public String getImgUrl(){
		return imgUrl;
	}
	public void setImgSort(Integer imgSort){
		this.imgSort = imgSort;
	}
	public Integer getImgSort(){
		return imgSort;
	}
	public void setDefaultImg(Integer defaultImg){
		this.defaultImg = defaultImg;
	}
	public Integer getDefaultImg(){
		return defaultImg;
	}
}