package com.rawchen.mall.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.rawchen.common.base.BaseService;
import com.rawchen.mall.product.mapper.AttrAttrgroupRelationMapper;
import com.rawchen.mall.product.entity.AttrAttrgroupRelation;

/**
 * 属性&属性分组关联 [AttrAttrgroupRelationService]服务实现.
 * Created by RawChen on 2021-12-30.
 **/
@Service
@Transactional(readOnly = false)
public class AttrAttrgroupRelationService extends BaseService<AttrAttrgroupRelationMapper, AttrAttrgroupRelation> {
	
}