package com.rawchen.mall.product.entity;

import com.rawchen.common.entity.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品评价回复关系 [CommentReplay]数据对象.
 * Created by RawChen on 2021-12-30.
 **/
@ApiModel(value = "CommentReplay", description = "商品评价回复关系对象")
public class CommentReplay extends DataEntity<CommentReplay> implements Serializable {

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 评论id
	 */
	@ApiModelProperty(value = "评论id")
	private Long commentId;

	/**
	 * 回复id
	 */
	@ApiModelProperty(value = "回复id")
	private Long replyId;

	public void setId(Long id){
		this.id = id;
	}
	public Long getId(){
		return id;
	}
	public void setCommentId(Long commentId){
		this.commentId = commentId;
	}
	public Long getCommentId(){
		return commentId;
	}
	public void setReplyId(Long replyId){
		this.replyId = replyId;
	}
	public Long getReplyId(){
		return replyId;
	}
}