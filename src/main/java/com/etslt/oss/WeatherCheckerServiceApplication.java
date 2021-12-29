package com.etslt.oss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.etslt.oss.entity.User;
import com.etslt.oss.repository.UserRepository;

@SpringBootApplication
public class WeatherCheckerServiceApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(WeatherCheckerServiceApplication.class, args);
	}
	
	public void run(String... args) {
		userRepository.save(new User(null, "user", "123", "user@123.com"));
	}

}
