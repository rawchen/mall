package com.rawchen.mall.product.dao;

import com.rawchen.mall.product.entity.SpuInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * spu信息
 * 
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-31 17:06:04
 */
@Mapper
public interface SpuInfoDao extends BaseMapper<SpuInfoEntity> {

	/**
	 * 修改上架成功的商品的状态
	 */
	void updateSpuStatus(@Param("spuId") Long spuId, @Param("code") int code);
}
