package com.etslt.oss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.etslt.oss.entity.User;
import com.etslt.oss.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/v1/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		user = userService.registerUser(user);
		System.out.println(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}
	
	@GetMapping("/test") 
	public String test(){
		return "Spring security - no auth";
	}
	

	
	@GetMapping("/api/test") 
	public String test1(){
		return "Spring security authenticated";
	}

}
