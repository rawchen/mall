package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * spu信息介绍 [SpuInfoDesc]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "SpuInfoDesc", description = "spu信息介绍对象")
public class SpuInfoDesc extends DataEntity<SpuInfoDesc> implements Serializable {

	/**
	 * 商品id
	 */
	@ApiModelProperty(value = "商品id")
	private Long spuId;

	/**
	 * 商品介绍
	 */
	@ApiModelProperty(value = "商品介绍")
	private String decript;

	public void setSpuId(Long spuId){
		this.spuId = spuId;
	}
	public Long getSpuId(){
		return spuId;
	}
	public void setDecript(String decript){
		this.decript = decript;
	}
	public String getDecript(){
		return decript;
	}
}