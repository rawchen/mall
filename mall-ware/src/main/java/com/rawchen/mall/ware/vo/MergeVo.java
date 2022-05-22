package com.rawchen.mall.ware.vo;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: MergeVo</p>
 * Description：
 * date：2022/1/6 23:23
 */
@Data
public class MergeVo {

	/**
	 * 整单id
	 */
	private Long purchaseId;

	/**
	 * [1,2,3,4]
	 * 合并项集合
	 */
	private List<Long> items;
}
