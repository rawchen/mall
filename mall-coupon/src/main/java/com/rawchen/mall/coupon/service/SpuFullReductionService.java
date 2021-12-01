package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.SpuFullReductionMapper;
import com.rawchen.mall.coupon.entity.SpuFullReduction;

/**
 * 商品满减信息 [SpuFullReductionService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class SpuFullReductionService extends BaseService<SpuFullReductionMapper, SpuFullReduction> {
	
}