package com.rawchen.mall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.rawchen.common.utils.PageUtils;
import com.rawchen.mall.member.entity.MemberEntity;
import com.rawchen.mall.member.exception.PhoneExistException;
import com.rawchen.mall.member.exception.UserNameExistException;
import com.rawchen.mall.member.vo.MemberLoginVo;
import com.rawchen.mall.member.vo.SocialUser;
import com.rawchen.mall.member.vo.UserRegisterVo;

import java.util.Map;

/**
 * 会员
 *
 * @author rawchen
 * @email rawchen@qq.com
 * @date 2021-12-30 00:49:16
 */
public interface MemberService extends IService<MemberEntity> {

    PageUtils queryPage(Map<String, Object> params);

	void register(UserRegisterVo userRegisterVo) throws PhoneExistException, UserNameExistException;

	void checkPhone(String phone) throws PhoneExistException;

	void checkUserName(String username) throws UserNameExistException;

	/**
	 * 普通登录
	 */
	MemberEntity login(MemberLoginVo vo);

	/**
	 * 社交登录
	 */
	MemberEntity login(SocialUser socialUser);
}

