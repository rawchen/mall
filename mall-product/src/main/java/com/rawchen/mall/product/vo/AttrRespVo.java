package com.rawchen.mall.product.vo;

import lombok.Data;

/**
 * <p>Title: AttrRespVo</p>
 * Description：
 * date：2022/1/2 19:56
 */
@Data
public class AttrRespVo extends AttrVo{

	private String catelogName;

	private String groupName;

	private Long[] catelogPath;
}
