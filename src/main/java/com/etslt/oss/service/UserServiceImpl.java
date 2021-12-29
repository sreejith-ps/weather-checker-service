package com.etslt.oss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.etslt.oss.entity.User;
import com.etslt.oss.repository.UserRepository;
import com.etslt.oss.security.config.UserPrincipal;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository; 

	@Override
	public User registerUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);

		if (null == user)
			throw new UsernameNotFoundException("User with name " + username + " doesn't exists");
		
		return new UserPrincipal(user);
	}

}
