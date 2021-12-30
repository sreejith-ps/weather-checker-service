package com.etslt.oss.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.etslt.oss.entity.User;
import com.etslt.oss.exception.UserAlreadyExistsException;
import com.etslt.oss.repository.UserRepository;
import com.etslt.oss.security.config.UserPrincipal;

@Service
public class UserServiceImpl implements UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserRepository userRepository; 

	@Override
	public User registerUser(User user) {
		
		User exists = userRepository.findByNameOrEmail(user.getName(), user.getEmail());
		
		if (null != exists) {
			logger.debug("User already exists with same name or email [" + user.getName() + " / " + user.getEmail()+ "]");
			throw new UserAlreadyExistsException("User already exists with same name or email [" + user.getName() + " / " + user.getEmail()+ "]");
		}
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
