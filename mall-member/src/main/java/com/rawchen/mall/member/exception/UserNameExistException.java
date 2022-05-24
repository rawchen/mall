package com.rawchen.mall.member.exception;

/**
 * <p>Title: UserNameExistException</p>
 * Description：
 * date：2022/1/25 19:17
 */
public class UserNameExistException extends RuntimeException {
	public UserNameExistException() {
		super("用户名存在");
	}
}
