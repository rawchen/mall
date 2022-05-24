package com.rawchen.mall.product.feign.fallback;

import com.rawchen.common.exception.BizCodeEnum;
import com.rawchen.common.utils.R;
import com.rawchen.mall.product.feign.SeckillFeignService;
import org.springframework.stereotype.Component;

/**
 * <p>Title: SecKillFeignServiceFalback</p>
 * Description：
 * date：2022/2/10 16:03
 */
@Component
public class SecKillFeignServiceFalback implements SeckillFeignService {

	@Override
	public R getSkuSeckillInfo(Long skuId) {
		System.out.println("触发熔断");
		return R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMsg());
	}
}
