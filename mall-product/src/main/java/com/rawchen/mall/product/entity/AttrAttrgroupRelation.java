package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 属性&属性分组关联 [AttrAttrgroupRelation]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "AttrAttrgroupRelation", description = "属性&属性分组关联对象")
public class AttrAttrgroupRelation extends DataEntity<AttrAttrgroupRelation> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 属性id
	 */
	@ApiModelProperty(value = "属性id")
	private Long attrId;

	/**
	 * 属性分组id
	 */
	@ApiModelProperty(value = "属性分组id")
	private Long attrGroupId;

	/**
	 * 属性组内排序
	 */
	@ApiModelProperty(value = "属性组内排序")
	private Integer attrSort;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setAttrId(Long attrId){
		this.attrId = attrId;
	}
	public Long getAttrId(){
		return attrId;
	}
	public void setAttrGroupId(Long attrGroupId){
		this.attrGroupId = attrGroupId;
	}
	public Long getAttrGroupId(){
		return attrGroupId;
	}
	public void setAttrSort(Integer attrSort){
		this.attrSort = attrSort;
	}
	public Integer getAttrSort(){
		return attrSort;
	}
}