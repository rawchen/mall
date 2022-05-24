package com.rawchen.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.product.entity.ProductAttrValueEntity;

import java.util.List;
import java.util.Map;

/**
 * spu属性值
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-31 17:06:04
 */
public interface ProductAttrValueService extends IService<ProductAttrValueEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void saveProductAttr(List<ProductAttrValueEntity> collect);

	/**
	 * 查询规格属性
	 */
	List<ProductAttrValueEntity> baseAttrListForSpu(Long spuId);

	/**
	 * 更新属性的规格
	 */
	void updateSpuAttr(Long spuId, List<ProductAttrValueEntity> entities);
}

