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
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetAllUsers = connection.prepareStatement(sqlGetAllUsers);
			
			ResultSet rsGetAllUsers = statementGetAllUsers.executeQuery();
			while (rsGetAllUsers.next()) {
				User user = new User();
				user.setUserId(rsGetAllUsers.getLong("user_id"));
				user.setFullName(rsGetAllUsers.getString("full_name"));
				user.setEmail(rsGetAllUsers.getString("email"));
				user.setUsername(rsGetAllUsers.getString("username"));
				user.setPassword(rsGetAllUsers.getString("password"));
				user.setRoleName(rsGetAllUsers.getString("role_name"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
                connection.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
		}
		return users;
	}
	
	public User getUserById(Long id) {
		User foundUser = null;
		String sqlGetUserById = "SELECT * FROM users WHERE user_id = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetUserById = connection.prepareStatement(sqlGetUserById);
			statementGetUserById.setLong(1, id);
			
			ResultSet rsGetUserById = statementGetUserById.executeQuery();
			if (rsGetUserById.next()) {
				foundUser = new User();
				foundUser.setUserId(rsGetUserById.getLong("user_id"));
				foundUser.setFullName(rsGetUserById.getString("full_name"));
				foundUser.setEmail(rsGetUserById.getString("email"));
				foundUser.setUsername(rsGetUserById.getString("username"));
				foundUser.setPassword(rsGetUserById.getString("password"));
				foundUser.setRoleName(rsGetUserById.getString("role_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
                connection.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
		}
		return foundUser;
	}
	
	public User getUserByUsername(String username) {
		User foundUser = null;
		String sqlGetUserByUsername = "SELECT * FROM users WHERE username = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetUserByUsername = connection.prepareStatement(sqlGetUserByUsername);
			statementGetUserByUsername.setString(1, username);
			
			ResultSet rsGetUserByUsername = statementGetUserByUsername.executeQuery();
			if (rsGetUserByUsername.next()) {
				foundUser = new User();
				foundUser.setUserId(rsGetUserByUsername.getLong("user_id"));
				foundUser.setFullName(rsGetUserByUsername.getString("full_name"));
				foundUser.setEmail(rsGetUserByUsername.getString("email"));
				foundUser.setUsername(rsGetUserByUsername.getString("username"));
				foundUser.setPassword(rsGetUserByUsername.getString("password"));
				foundUser.setRoleName(rsGetUserByUsername.getString("role_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
                connection.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
		}
		return foundUser;
	}
	
	public User getUserByEmail(String email) {
		User foundUser = null;
		String sqlGetUserByUsername = "SELECT * FROM users WHERE email = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetUserByUsername = connection.prepareStatement(sqlGetUserByUsername);
			statementGetUserByUsername.setString(1, email);
			
			ResultSet rsGetUserByUsername = statementGetUserByUsername.executeQuery();
			if (rsGetUserByUsername.next()) {
				foundUser = new User();
				foundUser.setUserId(rsGetUserByUsername.getLong("user_id"));
				foundUser.setFullName(rsGetUserByUsername.getString("full_name"));
				foundUser.setEmail(rsGetUserByUsername.getString("email"));
				foundUser.setUsername(rsGetUserByUsername.getString("username"));
				foundUser.setPassword(rsGetUserByUsername.getString("password"));
				foundUser.setRoleName(rsGetUserByUsername.getString("role_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
                connection.close();
	        } catch (SQLException closeEx) {
	            closeEx.printStackTrace();
	        }
		}
		return foundUser;
	}
	
	public boolean createUser(User newUser, Connection refConnection) {
		String sqlInsertUser = "INSERT INTO users(full_name, email, username, password, role_name) VALUES (?, ?, ?, ?, ?)";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
		try {
			PreparedStatement statementInsertUser = connection.prepareStatement(sqlInsertUser);
			statementInsertUser.setString(1, newUser.getFullName());
			statementInsertUser.setString(2, newUser.getEmail());
			statementInsertUser.setString(3, newUser.getUsername());
			statementInsertUser.setString(4, newUser.getPassword());
			statementInsertUser.setString(5, newUser.getRoleName());
			statementInsertUser.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(refConnection == null) {
				try {
	                connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		}
		return false;
	}
	
	public boolean updateUser(User updatedUser, Connection refConnection) {
		String sqlUpdateUser = "UPDATE users SET full_name = ?, email = ?, password = ?, role_name = ? WHERE user_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
        try {
            PreparedStatement statementUpdateUser = connection.prepareStatement(sqlUpdateUser);
            statementUpdateUser.setString(1, updatedUser.getFullName());
            statementUpdateUser.setString(2, updatedUser.getEmail());
            statementUpdateUser.setString(3, updatedUser.getPassword());
            statementUpdateUser.setString(4, updatedUser.getRoleName());
            statementUpdateUser.setLong(5, updatedUser.getUserId());
            statementUpdateUser.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if(refConnection == null) {
				try {
	                connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		}
        return false;
	}
	
	public boolean deleteUser(Long id, Connection refConnection) {
		String sqlDeleteUser = "DELETE FROM users WHERE user_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
        try {        	
        	// Xoá tất cả những dữ liệu liên quan User trước khi xoá User vì Database liên kết
        	
            PreparedStatement statementDeleteUser = connection.prepareStatement(sqlDeleteUser);
            statementDeleteUser.setLong(1, id);
            statementDeleteUser.executeUpdate();
            
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
			if(refConnection == null) {
				try {
	                connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		}
        return false;
	}

}
