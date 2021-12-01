package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.CouponSpuRelationMapper;
import com.rawchen.mall.coupon.entity.CouponSpuRelation;

/**
 * 优惠券与产品关联 [CouponSpuRelationService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class CouponSpuRelationService extends BaseService<CouponSpuRelationMapper, CouponSpuRelation> {
	
}