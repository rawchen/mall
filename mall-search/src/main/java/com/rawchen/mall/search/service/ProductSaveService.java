package com.rawchen.mall.search.service;

import com.rawchen.common.to.es.SkuEsModel;

import java.io.IOException;
import java.util.List;

/**
 * <p>Title: ProductSaveService</p>
 * Description：
 * date：2022/1/8 21:15
 */
public interface ProductSaveService {


	boolean productStatusUp(List<SkuEsModel> skuEsModels) throws IOException;
}
