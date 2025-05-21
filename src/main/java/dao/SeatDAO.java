package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infrastructure.MySQLDB;
import model.Seat;

public class SeatDAO {
	
	public List<Seat> getSeatsByShowId(Long showId) {
		List<Seat> seats = new ArrayList<Seat>();
		String sqlGetAllSeatsByShowId = "SELECT * FROM seats WHERE show_id = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetAllSeatsByShowId = connection.prepareStatement(sqlGetAllSeatsByShowId);
			statementGetAllSeatsByShowId.setLong(1, showId);
			
			ResultSet rsGetAllSeatsByShowId = statementGetAllSeatsByShowId.executeQuery();
			while (rsGetAllSeatsByShowId.next()) {
				Seat seat = new Seat();
				seat.setSeatId(rsGetAllSeatsByShowId.getLong("seat_id"));
				seat.setShowId(rsGetAllSeatsByShowId.getLong("show_id"));
				seat.setSeatNumber(rsGetAllSeatsByShowId.getString("seat_number"));
				seat.setPrice(rsGetAllSeatsByShowId.getLong("price"));
				seat.setDiscountPercentage(rsGetAllSeatsByShowId.getInt("discount_percentage"));
				seat.setAvailable(rsGetAllSeatsByShowId.getBoolean("available"));
				seats.add(seat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seats;
	}
				
	public boolean createSeat(Seat newSeat) {
		String sqlInsertSeat = "INSERT INTO seats(show_id, seat_number, price, discount_percentage, available) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementInsertSeat = connection.prepareStatement(sqlInsertSeat);
			statementInsertSeat.setLong(1, newSeat.getShowId());
			statementInsertSeat.setString(2, newSeat.getSeatNumber());
			statementInsertSeat.setLong(3, newSeat.getPrice());
			statementInsertSeat.setInt(4, newSeat.getDiscountPercentage());
			statementInsertSeat.setBoolean(5, newSeat.getAvailable());

			statementInsertSeat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateSeat(Seat updatedSeat) {
		String sqlUpdateSeat = "UPDATE seats SET available = ? WHERE seat_id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementUpdateSeat = connection.prepareStatement(sqlUpdateSeat);
            statementUpdateSeat.setBoolean(1, updatedSeat.getAvailable());
            statementUpdateSeat.setLong(2, updatedSeat.getSeatId());

            statementUpdateSeat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	public boolean deleteSeat(Long id) {
		String sqlDeleteSeat = "DELETE FROM seats WHERE seat_id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementDeleteSeat = connection.prepareStatement(sqlDeleteSeat);
            statementDeleteSeat.setLong(1, id);
            
            statementDeleteSeat.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
