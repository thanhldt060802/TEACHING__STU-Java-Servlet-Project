package model;

import java.sql.Timestamp;

public class User {

	private Long userId;
	private String fullName;
	private String email;
	private String username;
	private String password;
	private String roleName;

	public User() {
	}

	public User(Long userId, String fullName, String email, String username, String password, String roleName) {
		this.userId = userId;
		this.fullName = fullName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roleName = roleName;
	}

	public Long getUserId() {
		return this.userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
