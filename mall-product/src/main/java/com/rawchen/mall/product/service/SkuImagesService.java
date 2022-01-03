package com.rawchen.mall.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.product.mapper.SkuImagesMapper;
import com.rawchen.mall.product.entity.SkuImages;

/**
 * sku图片 [SkuImagesService]服务实现.
 * Created by RawChen on 2021-12-30.
 **/
@Service
@Transactional(readOnly = false)
public class SkuImagesService extends BaseService<SkuImagesMapper, SkuImages> {
	
}