package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.SeckillSkuNoticeMapper;
import com.rawchen.mall.coupon.entity.SeckillSkuNotice;

/**
 * 秒杀商品通知订阅 [SeckillSkuNoticeService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class SeckillSkuNoticeService extends BaseService<SeckillSkuNoticeMapper, SeckillSkuNotice> {
	
}