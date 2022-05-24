package com.rawchen.mall.search.service;

import com.rawchen.mall.search.vo.SearchParam;
import com.rawchen.mall.search.vo.SearchResult;

/**
 * <p>Title: MasllService</p>
 * Description：
 * date：2022/1/12 23:05
 */
public interface MallService {

	/**
	 * 检索所有参数
	 */
	SearchResult search(SearchParam Param);
}
