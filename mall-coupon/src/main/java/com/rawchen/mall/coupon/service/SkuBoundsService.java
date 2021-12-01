package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.SkuBoundsMapper;
import com.rawchen.mall.coupon.entity.SkuBounds;

/**
 * 商品sku积分设置 [SkuBoundsService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class SkuBoundsService extends BaseService<SkuBoundsMapper, SkuBounds> {
	
}