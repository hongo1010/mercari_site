package com.example.servise;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.repository.UserRepository;

@Service
@Transactional
public class RegisterService {
	
	@Autowired
	private UserRepository UserRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void insertUser(UserForm userForm) {
		User user = new User();
		BeanUtils.copyProperties(userForm, user);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setAuthority(1);
		UserRepository.insert(user);
	}

}
