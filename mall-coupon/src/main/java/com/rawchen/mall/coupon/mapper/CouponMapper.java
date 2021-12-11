package com.rawchen.mall.coupon.mapper;

import com.rawchen.mall.coupon.entity.Coupon;
import com.rawchen.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息 [Coupon]数据层数据库操作接口类.
 * Created by RawChen on 2021-11-22.
 **/
@Mapper
public interface CouponMapper extends BaseMapper<Coupon> {
	
}