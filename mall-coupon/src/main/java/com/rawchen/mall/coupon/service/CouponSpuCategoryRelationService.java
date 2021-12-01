package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.CouponSpuCategoryRelationMapper;
import com.rawchen.mall.coupon.entity.CouponSpuCategoryRelation;

/**
 * 优惠券分类关联 [CouponSpuCategoryRelationService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class CouponSpuCategoryRelationService extends BaseService<CouponSpuCategoryRelationMapper, CouponSpuCategoryRelation> {
	
}