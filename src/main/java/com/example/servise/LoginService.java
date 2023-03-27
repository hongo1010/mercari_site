package com.example.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.User;
import com.example.form.UserForm;
import com.example.repository.UserRepository;

@Service
@Transactional
public class LoginService {
	
	@Autowired
	private UserRepository UserRepository;
	
	public User loginUser(UserForm userForm) {
		return UserRepository.findByMailAndPassword(userForm.getUsername(), userForm.getPassword());
	}

}
