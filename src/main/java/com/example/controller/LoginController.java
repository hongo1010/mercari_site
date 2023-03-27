package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.servise.LoginService;

import jakarta.servlet.http.HttpSession;

/**
 * @author hongo
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private LoginService loginService;
	
	
	@GetMapping("")
	public String login(UserForm userForm) {
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout(UserForm userForm) {
		session.invalidate();
		return "login";
	}
}
