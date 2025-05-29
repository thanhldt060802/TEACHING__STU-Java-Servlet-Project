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
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetAllSeatsByShowId = connection.prepareStatement(sqlGetAllSeatsByShowId);
			statementGetAllSeatsByShowId.setLong(1, showId);
			
			ResultSet rsGetAllSeatsByShowId = statementGetAllSeatsByShowId.executeQuery();
			while (rsGetAllSeatsByShowId.next()) {
				Seat seat = new Seat();
				seat.setSeatId(rsGetAllSeatsByShowId.getLong("seat_id"));
				seat.setShowId(rsGetAllSeatsByShowId.getLong("show_id"));
				seat.setSeatNumber(rsGetAllSeatsByShowId.getString("seat_number"));
				seat.setAvailable(rsGetAllSeatsByShowId.getBoolean("available"));
				seats.add(seat);
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
		return seats;
	}
	
	public Seat getSeatById(Long seatId) {
		Seat foundSeat = null;
		String sqlGetSeatById = "SELECT * FROM seats WHERE seat_id = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetSeatById = connection.prepareStatement(sqlGetSeatById);
			statementGetSeatById.setLong(1, seatId);
			
			ResultSet rsGetSeatById = statementGetSeatById.executeQuery();
			if (rsGetSeatById.next()) {
				foundSeat = new Seat();
				foundSeat.setSeatId(rsGetSeatById.getLong("seat_id"));
				foundSeat.setShowId(rsGetSeatById.getLong("show_id"));
				foundSeat.setSeatNumber(rsGetSeatById.getString("seat_number"));
				foundSeat.setAvailable(rsGetSeatById.getBoolean("available"));
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
		return foundSeat;
	}
				
	public boolean createSeat(Seat newSeat, Connection refConnection) {
		String sqlInsertSeat = "INSERT INTO seats(show_id, seat_number, available) VALUES (?, ?, ?)";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
		try {
			PreparedStatement statementInsertSeat = connection.prepareStatement(sqlInsertSeat);
			statementInsertSeat.setLong(1, newSeat.getShowId());
			statementInsertSeat.setString(2, newSeat.getSeatNumber());
			statementInsertSeat.setBoolean(3, newSeat.getAvailable());
			statementInsertSeat.executeUpdate();
			
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
	
	public boolean updateSeat(Seat updatedSeat, Connection refConnection) {
		String sqlUpdateSeat = "UPDATE seats SET available = ? WHERE seat_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
        try {
            PreparedStatement statementUpdateSeat = connection.prepareStatement(sqlUpdateSeat);
            statementUpdateSeat.setBoolean(1, updatedSeat.getAvailable());
            statementUpdateSeat.setLong(2, updatedSeat.getSeatId());
            statementUpdateSeat.executeUpdate();
            
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
	
	public boolean deleteSeat(Long id, Connection refConnection) {
		String sqlDeleteSeat = "DELETE FROM seats WHERE seat_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
        try {
        	// Xoá tất cả những dữ liệu liên quan Seat trước khi xoá Seat vì Database liên kết
        	
            PreparedStatement statementDeleteSeat = connection.prepareStatement(sqlDeleteSeat);
            statementDeleteSeat.setLong(1, id);
            statementDeleteSeat.executeUpdate();
            
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
