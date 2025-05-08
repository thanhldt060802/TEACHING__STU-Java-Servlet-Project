package model;

import java.sql.Timestamp;

public class User {

	private Long id;
	private String fullName;
	private String email;
	private String username;
	private String hashedPassword;
	private String address;
	private String roleName;
	private Timestamp createdAt;
	private Timestamp updatedAt;

	public User() {
	}

	public User(Long id, String fullName, String email, String username, String hashedPassword, String address,
			String roleName, Timestamp createdAt, Timestamp updatedAt) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.username = username;
		this.hashedPassword = hashedPassword;
		this.address = address;
		this.roleName = roleName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}

}
