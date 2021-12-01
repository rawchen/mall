package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.SkuLadderMapper;
import com.rawchen.mall.coupon.entity.SkuLadder;

/**
 * 商品阶梯价格 [SkuLadderService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class SkuLadderService extends BaseService<SkuLadderMapper, SkuLadder> {
	
}