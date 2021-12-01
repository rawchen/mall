package com.rawchen.mall.coupon.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 专题商品 [HomeSubjectSpu]数据对象.
 * Created by RawChen on 2021-11-22.
 **/
@ApiModel(value = "HomeSubjectSpu", description = "专题商品对象")
public class HomeSubjectSpu extends DataEntity<HomeSubjectSpu> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 专题名字
	 */
	@ApiModelProperty(value = "专题名字")
	private String name;

	/**
	 * 专题id
	 */
	@ApiModelProperty(value = "专题id")
	private Long subjectId;

	/**
	 * spu_id
	 */
	@ApiModelProperty(value = "spu_id")
	private Long spuId;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setSubjectId(Long subjectId){
		this.subjectId = subjectId;
	}
	public Long getSubjectId(){
		return subjectId;
	}
	public void setSpuId(Long spuId){
		this.spuId = spuId;
	}
	public Long getSpuId(){
		return spuId;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return sort;
	}
}