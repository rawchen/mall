package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.MemberPriceMapper;
import com.rawchen.mall.coupon.entity.MemberPrice;

/**
 * 商品会员价格 [MemberPriceService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class MemberPriceService extends BaseService<MemberPriceMapper, MemberPrice> {
	
}