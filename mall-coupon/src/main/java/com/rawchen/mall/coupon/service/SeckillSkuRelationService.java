package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.SeckillSkuRelationMapper;
import com.rawchen.mall.coupon.entity.SeckillSkuRelation;

/**
 * 秒杀活动商品关联 [SeckillSkuRelationService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class SeckillSkuRelationService extends BaseService<SeckillSkuRelationMapper, SeckillSkuRelation> {
	
}