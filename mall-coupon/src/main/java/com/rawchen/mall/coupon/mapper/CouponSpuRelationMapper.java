package com.rawchen.mall.coupon.mapper;

import com.rawchen.mall.coupon.entity.CouponSpuRelation;
import com.rawchen.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券与产品关联 [CouponSpuRelation]数据层数据库操作接口类.
 * Created by RawChen on 2021-11-22.
 **/
@Mapper
public interface CouponSpuRelationMapper extends BaseMapper<CouponSpuRelation> {
	
}