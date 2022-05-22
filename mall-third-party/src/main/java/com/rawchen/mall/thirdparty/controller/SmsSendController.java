package com.rawchen.mall.thirdparty.controller;

import com.rawchen.common.exception.BizCodeEnum;
import com.rawchen.common.utils.R;
import com.rawchen.mall.thirdparty.component.SmsComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>Title: SmsSendController</p>
 * Description：
 * date：2022/1/25 14:53
 */
@Controller
@ResponseBody
@RequestMapping("/sms")
public class SmsSendController {

	@Autowired
	private SmsComponent smsComponent;

	/**
	 * 提供给别的服务进行调用的
	 */
	@GetMapping("/sendcode")
	public R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code){
		if(!"fail".equals(smsComponent.sendSmsCode(phone, code).split("_")[0])){
			return R.ok();
		}
		return R.error(BizCodeEnum.SMS_SEND_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_SEND_CODE_EXCEPTION.getMsg());
	}

	@GetMapping("/t")
	public R sendCode2() {
		return R.ok();
	}
}
