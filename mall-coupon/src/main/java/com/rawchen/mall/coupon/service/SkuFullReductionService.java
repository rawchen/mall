package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.SkuFullReductionMapper;
import com.rawchen.mall.coupon.entity.SkuFullReduction;

/**
 * 商品满减信息 [SkuFullReductionService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class SkuFullReductionService extends BaseService<SkuFullReductionMapper, SkuFullReduction> {
	
}