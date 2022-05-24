package com.rawchen.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.product.entity.CategoryEntity;
import com.rawchen.mall.product.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-31 17:06:04
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

	List<CategoryEntity> listWithTree();

	void removeMenuByIds(List<Long> asList);

	/**
	 * 找到catalogId 完整路径
	 */
	Long[] findCateLogPath(Long catelogId);

	/**
	 * 级联更新所有数据
	 */
	void updateCascade(CategoryEntity category);

	List<CategoryEntity> getLevel1Categorys();

	Map<String, List<Catelog2Vo>> getCatelogJson();
}

