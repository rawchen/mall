package com.rawchen.mall.auth.feign;

import com.rawchen.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Title: ThirdPartFeignService</p>
 * Description：
 * date：2022/1/25 15:01
 */
@FeignClient("mall-third-party")
public interface ThirdPartFeignService {

	@GetMapping("/sms/sendcode")
	R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
