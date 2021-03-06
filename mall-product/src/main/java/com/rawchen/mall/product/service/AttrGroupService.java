package com.rawchen.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.product.entity.AttrGroupEntity;
import com.rawchen.mall.product.vo.AttrGroupWithAttrsVo;
import com.rawchen.mall.product.vo.SpuItemAttrGroup;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-31 17:06:04
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryPage(Map<String, Object> params, Long catelogId);

	List<AttrGroupWithAttrsVo> getAttrGroupWithAttrByCatelogId(Long catelogId);

	List<SpuItemAttrGroup> getAttrGroupWithAttrsBySpuId(Long spuId, Long catalogId);
}

