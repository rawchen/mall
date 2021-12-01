package com.rawchen.mall.coupon.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.coupon.mapper.HomeAdvMapper;
import com.rawchen.mall.coupon.entity.HomeAdv;

/**
 * 首页轮播广告 [HomeAdvService]服务实现.
 * Created by RawChen on 2021-11-22.
 **/
@Service
@Transactional(readOnly = false)
public class HomeAdvService extends BaseService<HomeAdvMapper, HomeAdv> {
	
}