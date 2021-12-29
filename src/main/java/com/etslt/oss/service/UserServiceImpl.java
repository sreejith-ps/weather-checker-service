package com.etslt.oss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etslt.oss.entity.User;
import com.etslt.oss.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository; 

	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

}
