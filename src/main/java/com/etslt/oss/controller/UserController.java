package com.etslt.oss.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etslt.oss.entity.User;
import com.etslt.oss.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@PostMapping("/v1/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {

        HttpHeaders responseHeader = new HttpHeaders();
		user = userService.registerUser(user);
		return new ResponseEntity<>(user, responseHeader, HttpStatus.CREATED);
//		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

}
