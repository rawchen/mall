package com.rawchen.common.to.es;

import lombok.Data;

/**
 * <p>Title: SkuHasStockVo</p>
 * Description：存储这个sku是否有库存
 * date：2022/1/8 20:10
 */
@Data
public class SkuHasStockVo {

	private Long skuId;

	private Boolean hasStock;
}
