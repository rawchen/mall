package com.rawchen.mall.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.product.mapper.SpuImagesMapper;
import com.rawchen.mall.product.entity.SpuImages;

/**
 * spu图片 [SpuImagesService]服务实现.
 * Created by RawChen on 2021-12-30.
 **/
@Service
@Transactional(readOnly = false)
public class SpuImagesService extends BaseService<SpuImagesMapper, SpuImages> {
	
}