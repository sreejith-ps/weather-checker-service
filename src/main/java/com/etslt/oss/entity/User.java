package com.etslt.oss.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private Long userId;
	
	@NotNull(message = "Name can't be null")
	@NotEmpty(message = "Name can't be empty")
	@Size(min=3, message="Name should have atleast 3 characters")
    @Column(name = "name")
	private String name;
	
	@NotNull(message = "Email can't be null")
	@NotEmpty(message = "Email can't be empty")
	@Email(message = "Invalid Email format")
    @Column(name = "email")
	private String email;
	
	@NotNull(message = "Password can't be null")
	@NotEmpty(message = "Password can't be empty")
	@Size(min=3, message="Password should have atleast 3 characters")
    @Column(name = "password")
	private String password;
	
	public User() {}
	
	public User(Long userId, String name, String password, String email) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	public Long geUsertId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + "]";
	}
	

}
