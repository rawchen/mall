package com.rawchen.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.to.es.SkuHasStockVo;
import com.rawchen.common.to.mq.OrderTo;
import com.rawchen.common.to.mq.StockLockedTo;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.ware.entity.WareSkuEntity;
import com.rawchen.mall.ware.vo.WareSkuLockVo;

import java.util.List;
import java.util.Map;

/**
 * 商品库存
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2022-01-06 21:09:28
 */
public interface WareSkuService extends IService<WareSkuEntity> {

	void unlockStock(StockLockedTo to);

	PageUtils queryPage(Map<String, Object> params);

	/**
	 * 保存库存的时候顺便查到商品价格
	 */
	double addStock(Long skuId, Long wareId, Integer skuNum);

	/**
	 * 查询是否有库存
	 */
	List<SkuHasStockVo> getSkuHasStock(List<Long> skuIds);

	/**
	 * 为某个订单锁定库存
	 */
	Boolean orderLockStock(WareSkuLockVo vo);

	/**
	 * 由于订单超时而自动释放订单之后来解锁库存
	 */
	void unlockStock(OrderTo to);
}

