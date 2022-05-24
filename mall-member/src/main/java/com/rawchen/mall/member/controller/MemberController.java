package com.rawchen.mall.member.controller;

import com.rawchen.common.exception.BizCodeEnum;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.common.utils.R;
import com.rawchen.mall.member.entity.MemberEntity;
import com.rawchen.mall.member.exception.PhoneExistException;
import com.rawchen.mall.member.exception.UserNameExistException;
import com.rawchen.mall.member.feign.CouponFeignService;
import com.rawchen.mall.member.service.MemberService;
import com.rawchen.mall.member.vo.MemberLoginVo;
import com.rawchen.mall.member.vo.SocialUser;
import com.rawchen.mall.member.vo.UserRegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Map;



/**
 * 会员
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:49:16
 */
@RestController
@RequestMapping("member/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Resource
    private CouponFeignService couponFeignService;

	@PostMapping("/oauth2/login")
	public R login(@RequestBody SocialUser socialUser){

		MemberEntity memberEntity = memberService.login(socialUser);
		if(memberEntity != null){
			return R.ok().setData(memberEntity);
		}else {
			return R.error(BizCodeEnum.SOCIALUSER_LOGIN_ERROR.getCode(), BizCodeEnum.SOCIALUSER_LOGIN_ERROR.getMsg());
		}
	}

    @PostMapping("/login")
	public R login(@RequestBody MemberLoginVo vo){

		MemberEntity memberEntity = memberService.login(vo);
		if(memberEntity != null){
			return R.ok().setData(memberEntity);
		}else {
			return R.error(BizCodeEnum.LOGINACTT_PASSWORD_ERROR.getCode(), BizCodeEnum.LOGINACTT_PASSWORD_ERROR.getMsg());
		}
	}

    @PostMapping("/register")
    public R register(@RequestBody UserRegisterVo userRegisterVo){

		try {
			memberService.register(userRegisterVo);
		} catch (PhoneExistException e) {
			return R.error(BizCodeEnum.PHONE_EXIST_EXCEPTION.getCode(), BizCodeEnum.PHONE_EXIST_EXCEPTION.getMsg());
		} catch (UserNameExistException e) {
			return R.error(BizCodeEnum.USER_EXIST_EXCEPTION.getCode(), BizCodeEnum.USER_EXIST_EXCEPTION.getMsg());
		}
		return R.ok();
	}

	@RequestMapping("/coupons")
    public R test(){
		MemberEntity memberEntity = new MemberEntity();
		memberEntity.setNickname("rawchen");
		// 远程调用
		System.out.println(couponFeignService);
		R memberCoupons = couponFeignService.memberCoupons();
		return R.ok().put("member", memberEntity).put("coupons", memberCoupons.get("coupons"));
	}
    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:member:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:member:info")
    public R info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok().put("member", member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("${moduleNamez}:member:delete")
    public R delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
}
