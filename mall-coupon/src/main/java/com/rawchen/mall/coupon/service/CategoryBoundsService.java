package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.CategoryBoundsMapper;
import com.rawchen.mall.coupon.entity.CategoryBounds;

/**
 * 商品分类积分设置 [CategoryBoundsService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class CategoryBoundsService extends BaseService<CategoryBoundsMapper, CategoryBounds> {
	
}