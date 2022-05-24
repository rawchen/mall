package com.rawchen.mall.member.exception;

/**
 * <p>Title: PhoneExistException</p>
 * Description：
 * date：2022/1/25 19:17
 */
public class PhoneExistException extends RuntimeException {
	public PhoneExistException() {
		super("手机号存在");
	}
}
