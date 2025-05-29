package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infrastructure.MySQLDB;
import model.TicketSeat;

public class TicketSeatDAO {
	
	public List<TicketSeat> getAllTicketSeatsByTicketId(Long ticketId) {
		List<TicketSeat> ticketSeats = new ArrayList<TicketSeat>();
		String sqlGetAllTicketSeatsByTicketId = "SELECT * FROM ticket_seats WHERE ticket_id = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetAllTicketSeatsByTicketId = connection.prepareStatement(sqlGetAllTicketSeatsByTicketId);
			statementGetAllTicketSeatsByTicketId.setLong(1, ticketId);
			
			ResultSet rsGetAllTicketSeatsByTicketId = statementGetAllTicketSeatsByTicketId.executeQuery();
			while (rsGetAllTicketSeatsByTicketId.next()) {
				TicketSeat ticketSeat = new TicketSeat();
				ticketSeat.setTicketSeatId(rsGetAllTicketSeatsByTicketId.getLong("ticket_seat_id"));
				ticketSeat.setTicketId(rsGetAllTicketSeatsByTicketId.getLong("ticket_id"));
				ticketSeat.setSeatId(rsGetAllTicketSeatsByTicketId.getLong("seat_id"));
				ticketSeat.setPrice(rsGetAllTicketSeatsByTicketId.getLong("price"));
				ticketSeat.setDiscountPercentage(rsGetAllTicketSeatsByTicketId.getInt("discount_percentage"));
				ticketSeat.setTotalPrice(rsGetAllTicketSeatsByTicketId.getLong("total_price"));
				ticketSeats.add(ticketSeat);
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
		return ticketSeats;
	}
	
	public boolean createTicketSeat(TicketSeat newTicketSeat, Connection refConnection) {
		String sqlInsertTicketSeat = "INSERT INTO ticket_seats (ticket_id, seat_id, price, discount_percentage, total_price) VALUES (?, ?, ?, ?, ?)";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
		try {
			PreparedStatement statementInsertTicketSeat = connection.prepareStatement(sqlInsertTicketSeat);
			statementInsertTicketSeat.setLong(1, newTicketSeat.getTicketId());
			statementInsertTicketSeat.setLong(2, newTicketSeat.getSeatId());
			statementInsertTicketSeat.setLong(3, newTicketSeat.getPrice());
			statementInsertTicketSeat.setInt(4, newTicketSeat.getDiscountPercentage());
			statementInsertTicketSeat.setLong(5, newTicketSeat.getTotalPrice());
			statementInsertTicketSeat.executeUpdate();
			
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
	
	public boolean deleteTicketSeat(Long id, Connection refConnection) {
		String sqlDeleteTicketSeat = "DELETE FROM ticket_seats WHERE ticket_seat_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
        try {
        	// Xoá tất cả những dữ liệu liên quan TicketSeat trước khi xoá TicketSeat vì Database liên kết
        	
            PreparedStatement statementDeleteTicketSeat = connection.prepareStatement(sqlDeleteTicketSeat);
            statementDeleteTicketSeat.setLong(1, id);
            statementDeleteTicketSeat.executeUpdate();
            
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
