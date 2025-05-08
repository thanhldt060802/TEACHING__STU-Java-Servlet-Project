package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import infrastructure.MySQLDB;
import model.User;

public class UserDAO {
	
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

}
