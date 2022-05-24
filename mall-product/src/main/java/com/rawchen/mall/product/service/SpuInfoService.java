package com.rawchen.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.product.entity.SpuInfoEntity;
import com.rawchen.mall.product.vo.SpuSaveVo;

import java.util.Map;

/**
 * spu信息
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-31 17:06:04
 */
public interface SpuInfoService extends IService<SpuInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void saveSpuInfo(SpuSaveVo saveVo);

	void saveBatchSpuInfo(SpuInfoEntity spuInfoEntity);

	/**
	 * SPU模糊查询
	 */
	PageUtils queryPageByCondition(Map<String, Object> params);

	void up(Long spuId);

	/**
	 * 返回一个SpuEntity
	 */
	SpuInfoEntity getSpuInfoBySkuId(Long skuId);
}

