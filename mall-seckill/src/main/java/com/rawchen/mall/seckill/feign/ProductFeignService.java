package com.rawchen.mall.seckill.feign;

import com.rawchen.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: ProductFeignService</p>
 * Description：
 * date：2022/2/6 19:16
 */
@FeignClient("mall-product")
public interface ProductFeignService {

	@RequestMapping("/product/skuinfo/info/{skuId}")
	R skuInfo(@PathVariable("skuId") Long skuId);
}