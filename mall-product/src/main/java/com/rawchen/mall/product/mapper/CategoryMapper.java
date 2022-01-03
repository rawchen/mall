package com.rawchen.mall.product.mapper;

import com.rawchen.mall.product.entity.Category;
import com.rawchen.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类 [Category]数据层数据库操作接口类.
 * Created by RawChen on 2021-12-30.
 **/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
	
}