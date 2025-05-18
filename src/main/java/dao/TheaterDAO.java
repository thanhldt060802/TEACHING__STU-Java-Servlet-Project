package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infrastructure.MySQLDB;
import model.Theater;

public class TheaterDAO {
	
	public List<Theater> getTheaters() {
		List<Theater> theaters = new ArrayList<Theater>();
		String sqlGetAllTheaters = "SELECT * FROM theaters";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetAllTheaters = connection.prepareStatement(sqlGetAllTheaters);
			
			ResultSet rsGetAllTheaters = statementGetAllTheaters.executeQuery();
			while (rsGetAllTheaters.next()) {
				Theater theater = new Theater();
				theater.setTheaterId(rsGetAllTheaters.getLong("theater_id"));
				theater.setName(rsGetAllTheaters.getString("name"));
				theater.setLocation(rsGetAllTheaters.getString("location"));
				theaters.add(theater);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return theaters;
	}
	
	public Theater getTheaterById(Long id) {
		Theater foundTheater = null;
		String sqlGetTheaterById = "SELECT * FROM theaters WHERE theater_id = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetTheaterById = connection.prepareStatement(sqlGetTheaterById);
			statementGetTheaterById.setLong(1, id);
			
			ResultSet rsGetTheaterById = statementGetTheaterById.executeQuery();
			if (rsGetTheaterById.next()) {
				foundTheater = new Theater();
				foundTheater.setTheaterId(rsGetTheaterById.getLong("theater_id"));
				foundTheater.setName(rsGetTheaterById.getString("name"));
				foundTheater.setLocation(rsGetTheaterById.getString("location"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundTheater;
	}
			
	public boolean createTheater(Theater newTheater) {
		String sqlInsertTheater = "INSERT INTO theaters(name, location) VALUES (?, ?)";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementInsertTheater = connection.prepareStatement(sqlInsertTheater);
			statementInsertTheater.setString(1, newTheater.getName());
			statementInsertTheater.setString(2, newTheater.getLocation());

			statementInsertTheater.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateTheater(Theater updatedTheater) {
		String sqlUpdateTheater = "UPDATE theaters SET name = ?, location = ? WHERE theater_id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementUpdateTheater = connection.prepareStatement(sqlUpdateTheater);
            statementUpdateTheater.setString(1, updatedTheater.getName());
            statementUpdateTheater.setString(2, updatedTheater.getLocation());

            statementUpdateTheater.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	public boolean deleteTheater(Long id) {
		String sqlDeleteTheater = "DELETE FROM theaters WHERE theater_id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementDeleteTheater = connection.prepareStatement(sqlDeleteTheater);
            statementDeleteTheater.setLong(1, id);
            
            statementDeleteTheater.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
