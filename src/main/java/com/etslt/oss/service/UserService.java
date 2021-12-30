package com.etslt.oss.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.etslt.oss.entity.User;

public interface UserService extends UserDetailsService {
	User registerUser(User user);
}
