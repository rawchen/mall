package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 商品满减信息 [SpuFullReduction]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "SpuFullReduction", description = "商品满减信息对象")
public class SpuFullReduction extends DataEntity<SpuFullReduction> implements Serializable {

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
	 * 满多少
	 */
	@ApiModelProperty(value = "满多少")
	private java.lang.Float fullPrice;

	/**
	 * 减多少
	 */
	@ApiModelProperty(value = "减多少")
	private java.lang.Float reducePrice;

	/**
	 * 是否参与其他优惠
	 */
	@ApiModelProperty(value = "是否参与其他优惠")
	private Integer addOther;

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
	public void setFullPrice(java.lang.Float fullPrice){
		this.fullPrice = fullPrice;
	}
	public java.lang.Float getFullPrice(){
		return fullPrice;
	}
	public void setReducePrice(java.lang.Float reducePrice){
		this.reducePrice = reducePrice;
	}
	public java.lang.Float getReducePrice(){
		return reducePrice;
	}
	public void setAddOther(Integer addOther){
		this.addOther = addOther;
	}
	public Integer getAddOther(){
		return addOther;
	}
}