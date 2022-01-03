package com.rawchen.mall.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.product.mapper.SkuInfoMapper;
import com.rawchen.mall.product.entity.SkuInfo;

/**
 * sku信息 [SkuInfoService]服务实现.
 * Created by RawChen on 2021-12-30.
 **/
@Service
@Transactional(readOnly = false)
public class SkuInfoService extends BaseService<SkuInfoMapper, SkuInfo> {
	
}