package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品评价 [SpuComment]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "SpuComment", description = "商品评价对象")
public class SpuComment extends DataEntity<SpuComment> implements Serializable {

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
	 * spu_id
	 */
	@ApiModelProperty(value = "spu_id")
	private Long spuId;

	/**
	 * 商品名字
	 */
	@ApiModelProperty(value = "商品名字")
	private String spuName;

	/**
	 * 会员昵称
	 */
	@ApiModelProperty(value = "会员昵称")
	private String memberNickName;

	/**
	 * 星级
	 */
	@ApiModelProperty(value = "星级")
	private Integer star;

	/**
	 * 会员ip
	 */
	@ApiModelProperty(value = "会员ip")
	private String memberIp;

	/**
	 * 显示状态[0-不显示，1-显示]
	 */
	@ApiModelProperty(value = "显示状态[0-不显示，1-显示]")
	private Integer showStatus;

	/**
	 * 购买时属性组合
	 */
	@ApiModelProperty(value = "购买时属性组合")
	private String spuAttributes;

	/**
	 * 点赞数
	 */
	@ApiModelProperty(value = "点赞数")
	private Integer likesCount;

	/**
	 * 回复数
	 */
	@ApiModelProperty(value = "回复数")
	private Integer replyCount;

	/**
	 * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
	 */
	@ApiModelProperty(value = "评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]")
	private String resources;

	/**
	 * 内容
	 */
	@ApiModelProperty(value = "内容")
	private String content;

	/**
	 * 用户头像
	 */
	@ApiModelProperty(value = "用户头像")
	private String memberIcon;

	/**
	 * 评论类型[0 - 对商品的直接评论，1 - 对评论的回复]
	 */
	@ApiModelProperty(value = "评论类型[0 - 对商品的直接评论，1 - 对评论的回复]")
	private Integer commentType;

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
	public void setSpuId(Long spuId){
		this.spuId = spuId;
	}
	public Long getSpuId(){
		return spuId;
	}
	public void setSpuName(String spuName){
		this.spuName = spuName;
	}
	public String getSpuName(){
		return spuName;
	}
	public void setMemberNickName(String memberNickName){
		this.memberNickName = memberNickName;
	}
	public String getMemberNickName(){
		return memberNickName;
	}
	public void setStar(Integer star){
		this.star = star;
	}
	public Integer getStar(){
		return star;
	}
	public void setMemberIp(String memberIp){
		this.memberIp = memberIp;
	}
	public String getMemberIp(){
		return memberIp;
	}
	public void setShowStatus(Integer showStatus){
		this.showStatus = showStatus;
	}
	public Integer getShowStatus(){
		return showStatus;
	}
	public void setSpuAttributes(String spuAttributes){
		this.spuAttributes = spuAttributes;
	}
	public String getSpuAttributes(){
		return spuAttributes;
	}
	public void setLikesCount(Integer likesCount){
		this.likesCount = likesCount;
	}
	public Integer getLikesCount(){
		return likesCount;
	}
	public void setReplyCount(Integer replyCount){
		this.replyCount = replyCount;
	}
	public Integer getReplyCount(){
		return replyCount;
	}
	public void setResources(String resources){
		this.resources = resources;
	}
	public String getResources(){
		return resources;
	}
	public void setContent(String content){
		this.content = content;
	}
	public String getContent(){
		return content;
	}
	public void setMemberIcon(String memberIcon){
		this.memberIcon = memberIcon;
	}
	public String getMemberIcon(){
		return memberIcon;
	}
	public void setCommentType(Integer commentType){
		this.commentType = commentType;
	}
	public Integer getCommentType(){
		return commentType;
	}
}