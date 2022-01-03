package com.rawchen.mall.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.product.mapper.CategoryMapper;
import com.rawchen.mall.product.entity.Category;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 商品三级分类 [CategoryService]服务实现.
 * Created by RawChen on 2021-12-30.
 **/
@Service
@Transactional(readOnly = false)
public class CategoryService extends BaseService<CategoryMapper, Category> {

	@Autowired
	CategoryMapper mapper;

	/**
	 * 组装树结构
	 *
	 * @return
	 */
	public List<Category> listWithTree() {
		List<Category> categories = mapper.findList(new Category());

		//找到所有的一级分类并找到对应的children
		return categories
				.stream()
				.filter(category -> category.getParentCid() == 0)
				.peek(menu -> menu.setChildren(getChildren(menu, categories)))
				.collect(Collectors.toList());
	}

	/**
	 * 递归查找所有菜单的子菜单
	 *
	 * @param root
	 * @param all
	 * @return
	 */
	private List<Category> getChildren(Category root, List<Category> all) {

		//找到子菜单并对菜单排序
		return all.stream()
				.filter(category -> Objects.equals(category.getParentCid(), root.getCatId()))
				.peek(category -> category.setChildren(getChildren(category, all)))
				.sorted(Comparator.comparingInt(Category::getSort))
				.collect(Collectors.toList());
	}
}