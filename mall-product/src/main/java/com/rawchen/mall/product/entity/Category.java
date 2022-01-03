package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 商品三级分类 [Category]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "Category", description = "商品三级分类对象")
public class Category extends DataEntity<Category> implements Serializable {

	/**
	 * 分类id
	 */
	@ApiModelProperty(value = "分类id")
	private Long catId;

	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称")
	private String name;

	/**
	 * 父分类id
	 */
	@ApiModelProperty(value = "父分类id")
	private Long parentCid;

	/**
	 * 层级
	 */
	@ApiModelProperty(value = "层级")
	private Integer catLevel;

	/**
	 * 是否显示[0-不显示，1显示]
	 */
	@ApiModelProperty(value = "是否显示[0-不显示，1显示]")
	private Integer showStatus;

	/**
	 * 排序
	 */
	@ApiModelProperty(value = "排序")
	private Integer sort;

	/**
	 * 图标地址
	 */
	@ApiModelProperty(value = "图标地址")
	private String icon;

	/**
	 * 计量单位
	 */
	@ApiModelProperty(value = "计量单位")
	private String productUnit;

	/**
	 * 商品数量
	 */
	@ApiModelProperty(value = "商品数量")
	private Integer productCount;

	/**
	 * 子分类
	 */
	@ApiModelProperty(value = "子分类")
	private List<Category> children;

	public void setCatId(Long catId){
		this.catId = catId;
	}
	public Long getCatId(){
		return catId;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	public void setParentCid(Long parentCid){
		this.parentCid = parentCid;
	}
	public Long getParentCid(){
		return parentCid;
	}
	public void setCatLevel(Integer catLevel){
		this.catLevel = catLevel;
	}
	public Integer getCatLevel(){
		return catLevel;
	}
	public void setShowStatus(Integer showStatus){
		this.showStatus = showStatus;
	}
	public Integer getShowStatus(){
		return showStatus;
	}
	public void setSort(Integer sort){
		this.sort = sort;
	}
	public Integer getSort(){
		return sort;
	}
	public void setIcon(String icon){
		this.icon = icon;
	}
	public String getIcon(){
		return icon;
	}
	public void setProductUnit(String productUnit){
		this.productUnit = productUnit;
	}
	public String getProductUnit(){
		return productUnit;
	}
	public void setProductCount(Integer productCount){
		this.productCount = productCount;
	}
	public Integer getProductCount(){
		return productCount;
	}

	public List<Category> getChildren() {
		return children;
	}

	public void setChildren(List<Category> children) {
		this.children = children;
	}
}