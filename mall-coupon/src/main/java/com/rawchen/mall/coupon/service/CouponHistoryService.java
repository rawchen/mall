package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.CouponHistoryMapper;
import com.rawchen.mall.coupon.entity.CouponHistory;

/**
 * 优惠券领取历史记录 [CouponHistoryService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class CouponHistoryService extends BaseService<CouponHistoryMapper, CouponHistory> {
	
}