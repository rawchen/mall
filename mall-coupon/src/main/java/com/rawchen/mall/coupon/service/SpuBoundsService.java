package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.SpuBoundsMapper;
import com.rawchen.mall.coupon.entity.SpuBounds;

/**
 * 商品spu积分设置 [SpuBoundsService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class SpuBoundsService extends BaseService<SpuBoundsMapper, SpuBounds> {
	
}