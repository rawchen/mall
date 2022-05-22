package com.rawchen.mall.coupon.dao;

import com.rawchen.mall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:57:53
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
