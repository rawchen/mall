package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 属性分组 [AttrGroup]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "AttrGroup", description = "属性分组对象")
public class AttrGroup extends DataEntity<AttrGroup> implements Serializable {

	/**
	 * 分组id
	 */
	@ApiModelProperty(value = "分组id")
	private Long attrGroupId;

	/**
	 * 组名
	 */
	@ApiModelProperty(value = "组名")
	private String attrGroupName;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述")
	private String descript;

	/**
	 * 组图标
	 */
	@ApiModelProperty(value = "组图标")
	private String icon;

	/**
	 * 所属分类id
	 */
	@ApiModelProperty(value = "所属分类id")
	private Long catelogId;

	public void setAttrGroupId(Long attrGroupId){
		this.attrGroupId = attrGroupId;
	}
	public Long getAttrGroupId(){
		return attrGroupId;
	}
	public void setAttrGroupName(String attrGroupName){
		this.attrGroupName = attrGroupName;
	}
	public String getAttrGroupName(){
		return attrGroupName;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return sort;
	}
	public void setDescript(String descript){
		this.descript = descript;
	}
	public String getDescript(){
		return descript;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getIcon(){
		return icon;
	}
	public void setCatelogId(Long catelogId){
		this.catelogId = catelogId;
	}
	public Long getCatelogId(){
		return catelogId;
	}
}