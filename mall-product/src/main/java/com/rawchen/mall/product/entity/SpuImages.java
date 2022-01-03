package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * spu图片 [SpuImages]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "SpuImages", description = "spu图片对象")
public class SpuImages extends DataEntity<SpuImages> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * spu_id
	 */
	@ApiModelProperty(value = "spu_id")
	private Long spuId;

	/**
	 * 图片名
	 */
	@ApiModelProperty(value = "图片名")
	private String imgName;

	/**
	 * 图片地址
	 */
	@ApiModelProperty(value = "图片地址")
	private String imgUrl;

	/**
	 * 顺序
	 */
	@ApiModelProperty(value = "顺序")
	private Integer imgSort;

	/**
	 * 是否默认图
	 */
	@ApiModelProperty(value = "是否默认图")
	private Integer defaultImg;

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
	public void setImgName(String imgName){
		this.imgName = imgName;
	}
	public String getImgName(){
		return imgName;
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