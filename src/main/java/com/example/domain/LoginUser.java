package com.example.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class LoginUser extends org.springframework.security.core.userdetails.User { 
	
	private static final long serialVersionUID = 1L;
	private final User user;
	
	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getName(), user.getPassword(), authorityList);
		this.user = user;
	}

	/**
	 * ユーザ情報を返します.
	 * 
	 * @return ユーザ情報
	 */
	public User getUser() {
		System.out.println("ログインユーザー情報" + user);
		return user;
	}

}
