package com.etslt.oss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.etslt.oss.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String userName);
	User findByNameOrEmail(String userName, String email);
}
