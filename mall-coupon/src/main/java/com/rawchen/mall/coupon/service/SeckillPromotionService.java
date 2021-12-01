package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.SeckillPromotionMapper;
import com.rawchen.mall.coupon.entity.SeckillPromotion;

/**
 * 秒杀活动 [SeckillPromotionService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class SeckillPromotionService extends BaseService<SeckillPromotionMapper, SeckillPromotion> {
	
}