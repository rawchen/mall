package com.rawchen.mall.product.dao;

import com.rawchen.mall.product.entity.SkuSaleAttrValueEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rawchen.mall.product.vo.ItemSaleAttrVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * sku销售属性&值
 * 
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-31 17:06:04
 */
@Mapper
public interface SkuSaleAttrValueDao extends BaseMapper<SkuSaleAttrValueEntity> {

	List<ItemSaleAttrVo> getSaleAttrsBuSpuId(@Param("spuId") Long spuId);

	List<String> getSkuSaleAttrValuesAsStringList(@Param("skuId") Long skuId);
}
