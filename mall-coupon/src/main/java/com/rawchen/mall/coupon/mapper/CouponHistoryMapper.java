package com.rawchen.mall.coupon.mapper;

import com.rawchen.mall.coupon.entity.CouponHistory;
import com.rawchen.common.base.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券领取历史记录 [CouponHistory]数据层数据库操作接口类.
 * Created by RawChen on 2021-11-22.
 **/
@Mapper
public interface CouponHistoryMapper extends BaseMapper<CouponHistory> {
	
}