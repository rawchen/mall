package com.rawchen.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.to.SkuReductionTO;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.coupon.entity.SkuFullReductionEntity;

import java.util.Map;

/**
 * 商品满减信息
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:57:53
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void saveSkuReduction(SkuReductionTO skuReductionTO);
}

