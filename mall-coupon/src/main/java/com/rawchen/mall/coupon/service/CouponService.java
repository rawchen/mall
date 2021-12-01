package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.CouponMapper;
import com.rawchen.mall.coupon.entity.Coupon;

/**
 * 优惠券信息 [CouponService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class CouponService extends BaseService<CouponMapper, Coupon> {
	
}