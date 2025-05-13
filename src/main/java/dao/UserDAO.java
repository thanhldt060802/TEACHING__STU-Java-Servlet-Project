package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infrastructure.MySQLDB;
import model.User;

public class UserDAO {
	
	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		String sqlGetAllUsers = "SELECT * FROM users";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetAllUsers = connection.prepareStatement(sqlGetAllUsers);
			
			ResultSet rsGetAllUsers = statementGetAllUsers.executeQuery();
			while (rsGetAllUsers.next()) {
				User user = new User();
				user.setId(rsGetAllUsers.getLong("id"));
				user.setFullName(rsGetAllUsers.getString("full_name"));
				user.setEmail(rsGetAllUsers.getString("email"));
				user.setUsername(rsGetAllUsers.getString("username"));
				user.setHashedPassword(rsGetAllUsers.getString("hashed_password"));
				user.setAddress(rsGetAllUsers.getString("address"));
				user.setRoleName(rsGetAllUsers.getString("role_name"));
				user.setCreatedAt(rsGetAllUsers.getTimestamp("created_at"));
				user.setUpdatedAt(rsGetAllUsers.getTimestamp("updated_at"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public User getUserById(Long id) {
		User foundUser = null;
		String sqlGetUserById = "SELECT * FROM users WHERE id = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetUserById = connection.prepareStatement(sqlGetUserById);
			statementGetUserById.setLong(1, id);
			
			ResultSet rsGetUserById = statementGetUserById.executeQuery();
			if (rsGetUserById.next()) {
				foundUser = new User();
				foundUser.setId(rsGetUserById.getLong("id"));
				foundUser.setFullName(rsGetUserById.getString("full_name"));
				foundUser.setEmail(rsGetUserById.getString("email"));
				foundUser.setUsername(rsGetUserById.getString("username"));
				foundUser.setHashedPassword(rsGetUserById.getString("hashed_password"));
				foundUser.setAddress(rsGetUserById.getString("address"));
				foundUser.setRoleName(rsGetUserById.getString("role_name"));
				foundUser.setCreatedAt(rsGetUserById.getTimestamp("created_at"));
				foundUser.setUpdatedAt(rsGetUserById.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundUser;
	}
	
	public User getUserByUsername(String username) {
		User foundUser = null;
		String sqlGetUserByUsername = "SELECT * FROM users WHERE username = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetUserByUsername = connection.prepareStatement(sqlGetUserByUsername);
			statementGetUserByUsername.setString(1, username);
			
			ResultSet rsGetUserByUsername = statementGetUserByUsername.executeQuery();
			if (rsGetUserByUsername.next()) {
				foundUser = new User();
				foundUser.setId(rsGetUserByUsername.getLong("id"));
				foundUser.setFullName(rsGetUserByUsername.getString("full_name"));
				foundUser.setEmail(rsGetUserByUsername.getString("email"));
				foundUser.setUsername(rsGetUserByUsername.getString("username"));
				foundUser.setHashedPassword(rsGetUserByUsername.getString("hashed_password"));
				foundUser.setAddress(rsGetUserByUsername.getString("address"));
				foundUser.setRoleName(rsGetUserByUsername.getString("role_name"));
				foundUser.setCreatedAt(rsGetUserByUsername.getTimestamp("created_at"));
				foundUser.setUpdatedAt(rsGetUserByUsername.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundUser;
	}
	
	public User getUserByEmail(String email) {
		User foundUser = null;
		String sqlGetUserByUsername = "SELECT * FROM users WHERE email = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetUserByUsername = connection.prepareStatement(sqlGetUserByUsername);
			statementGetUserByUsername.setString(1, email);
			
			ResultSet rsGetUserByUsername = statementGetUserByUsername.executeQuery();
			if (rsGetUserByUsername.next()) {
				foundUser = new User();
				foundUser.setId(rsGetUserByUsername.getLong("id"));
				foundUser.setFullName(rsGetUserByUsername.getString("full_name"));
				foundUser.setEmail(rsGetUserByUsername.getString("email"));
				foundUser.setUsername(rsGetUserByUsername.getString("username"));
				foundUser.setHashedPassword(rsGetUserByUsername.getString("hashed_password"));
				foundUser.setAddress(rsGetUserByUsername.getString("address"));
				foundUser.setRoleName(rsGetUserByUsername.getString("role_name"));
				foundUser.setCreatedAt(rsGetUserByUsername.getTimestamp("created_at"));
				foundUser.setUpdatedAt(rsGetUserByUsername.getTimestamp("updated_at"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundUser;
	}
	
	public boolean createUser(User newUser) {
		String sqlInsertUser = "INSERT INTO users(full_name, email, username, hashed_password, address, role_name) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementInsertUser = connection.prepareStatement(sqlInsertUser);
			statementInsertUser.setString(1, newUser.getFullName());
			statementInsertUser.setString(2, newUser.getEmail());
			statementInsertUser.setString(3, newUser.getUsername());
			statementInsertUser.setString(4, newUser.getHashedPassword());
			statementInsertUser.setString(5, newUser.getAddress());
			statementInsertUser.setString(6, newUser.getRoleName());

			statementInsertUser.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateUser(User updatedUser) {
		String sqlUpdateUser = "UPDATE users SET full_name = ?, email = ?, hashed_password = ?, address = ?, role_name = ? WHERE id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementUpdateUser = connection.prepareStatement(sqlUpdateUser);
            statementUpdateUser.setString(1, updatedUser.getFullName());
            statementUpdateUser.setString(2, updatedUser.getEmail());
            statementUpdateUser.setString(3, updatedUser.getHashedPassword());
            statementUpdateUser.setString(4, updatedUser.getAddress());
            statementUpdateUser.setString(5, updatedUser.getRoleName());
            statementUpdateUser.setLong(6, updatedUser.getId());

            statementUpdateUser.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	public boolean deleteUser(Long id) {
		String sqlDeleteUser = "DELETE FROM users WHERE id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementDeleteUser = connection.prepareStatement(sqlDeleteUser);
            statementDeleteUser.setLong(1, id);
            
            statementDeleteUser.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
