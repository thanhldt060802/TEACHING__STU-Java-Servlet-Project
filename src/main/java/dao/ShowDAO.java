package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infrastructure.MySQLDB;
import model.Seat;
import model.Show;

public class ShowDAO {
	
	public List<Show> getShows() {
		List<Show> shows = new ArrayList<Show>();
		String sqlGetAllShows = "SELECT * FROM shows";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetAllShows = connection.prepareStatement(sqlGetAllShows);
			
			ResultSet rsGetAllShows = statementGetAllShows.executeQuery();
			while (rsGetAllShows.next()) {
				Show show = new Show();
				show.setShowId(rsGetAllShows.getLong("show_id"));
				show.setMovieId(rsGetAllShows.getLong("movie_id"));
				show.setTheaterId(rsGetAllShows.getLong("theater_id"));
				show.setStartAt(rsGetAllShows.getTimestamp("start_at"));
				show.setPrice(rsGetAllShows.getLong("price"));
				show.setDiscountPercentage(rsGetAllShows.getInt("discount_percentage"));
				shows.add(show);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shows;
	}
	
	public List<Show> getShowsByMovieId(Long movieId) {
		List<Show> shows = new ArrayList<Show>();
		String sqlGetAllShowsByMovieId = "SELECT * FROM shows WHERE movie_id = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetAllShowsByMovieId = connection.prepareStatement(sqlGetAllShowsByMovieId);
			statementGetAllShowsByMovieId.setLong(1, movieId);
			
			ResultSet rsGetAllShowsByMovieId = statementGetAllShowsByMovieId.executeQuery();
			while (rsGetAllShowsByMovieId.next()) {
				Show show = new Show();
				show.setShowId(rsGetAllShowsByMovieId.getLong("show_id"));
				show.setMovieId(rsGetAllShowsByMovieId.getLong("movie_id"));
				show.setTheaterId(rsGetAllShowsByMovieId.getLong("theater_id"));
				show.setStartAt(rsGetAllShowsByMovieId.getTimestamp("start_at"));
				show.setPrice(rsGetAllShowsByMovieId.getLong("price"));
				show.setDiscountPercentage(rsGetAllShowsByMovieId.getInt("discount_percentage"));
				shows.add(show);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shows;
	}

	public List<Show> getShowsByTheaterId(Long theaterId) {
		List<Show> shows = new ArrayList<Show>();
		String sqlGetAllShowsByTheaterId = "SELECT * FROM shows WHERE theater_id = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetAllShowsByTheaterId = connection.prepareStatement(sqlGetAllShowsByTheaterId);
			statementGetAllShowsByTheaterId.setLong(1, theaterId);
			
			ResultSet rsGetAllShowsByTheaterId = statementGetAllShowsByTheaterId.executeQuery();
			while (rsGetAllShowsByTheaterId.next()) {
				Show show = new Show();
				show.setShowId(rsGetAllShowsByTheaterId.getLong("show_id"));
				show.setMovieId(rsGetAllShowsByTheaterId.getLong("movie_id"));
				show.setTheaterId(rsGetAllShowsByTheaterId.getLong("theater_id"));
				show.setStartAt(rsGetAllShowsByTheaterId.getTimestamp("start_at"));
				show.setPrice(rsGetAllShowsByTheaterId.getLong("price"));
				show.setDiscountPercentage(rsGetAllShowsByTheaterId.getInt("discount_percentage"));
				shows.add(show);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shows;
	}
	
	public Show getShowById(Long id) {
		Show foundShow = null;
		String sqlGetShowById = "SELECT * FROM shows WHERE show_id = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetShowById = connection.prepareStatement(sqlGetShowById);
			statementGetShowById.setLong(1, id);
			
			ResultSet rsGetShowById = statementGetShowById.executeQuery();
			if (rsGetShowById.next()) {
				foundShow = new Show();
				foundShow.setShowId(rsGetShowById.getLong("show_id"));
				foundShow.setMovieId(rsGetShowById.getLong("movie_id"));
				foundShow.setTheaterId(rsGetShowById.getLong("theater_id"));
				foundShow.setStartAt(rsGetShowById.getTimestamp("start_at"));
				foundShow.setPrice(rsGetShowById.getLong("price"));
				foundShow.setDiscountPercentage(rsGetShowById.getInt("discount_percentage"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundShow;
	}
			
	public boolean createShow(Show newShow) {
		String sqlInsertShow = "INSERT INTO shows(movie_id, theater_id, start_at, price, discount_percentage) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementInsertShow = connection.prepareStatement(sqlInsertShow);
			statementInsertShow.setLong(1, newShow.getMovieId());
			statementInsertShow.setLong(2, newShow.getTheaterId());
			statementInsertShow.setTimestamp(3, newShow.getStartAt());
			statementInsertShow.setLong(4, newShow.getPrice());
			statementInsertShow.setInt(5, newShow.getDiscountPercentage());

			statementInsertShow.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateShow(Show updatedShow) {
		String sqlUpdateShow = "UPDATE shows SET start_at = ?, price = ?, discount_percentage = ? WHERE show_id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementUpdateShow = connection.prepareStatement(sqlUpdateShow);
            statementUpdateShow.setTimestamp(1, updatedShow.getStartAt());
            statementUpdateShow.setLong(2,  updatedShow.getPrice());
            statementUpdateShow.setInt(3, updatedShow.getDiscountPercentage());
            statementUpdateShow.setLong(4, updatedShow.getShowId());

            statementUpdateShow.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	public boolean deleteShow(Long id) {
		String sqlDeleteShow = "DELETE FROM shows WHERE show_id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementDeleteShow = connection.prepareStatement(sqlDeleteShow);
            statementDeleteShow.setLong(1, id);
            
            statementDeleteShow.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
