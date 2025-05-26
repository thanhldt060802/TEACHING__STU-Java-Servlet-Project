package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import infrastructure.MySQLDB;
import model.TicketSeat;

public class TicketSeatDAO {
	
	public boolean createTicketSeat(TicketSeat newTicketSeat) {
		String sqlInsertTicketSeat = "INSERT INTO ticket_seats(ticket_id, seat_id, price, discount) VALUES (?, ?, ?, ?)";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementInsertTicketSeat = connection.prepareStatement(sqlInsertTicketSeat);
			statementInsertTicketSeat.setLong(1, newTicketSeat.getTicketId());
			statementInsertTicketSeat.setLong(2, newTicketSeat.getSeatId());
			statementInsertTicketSeat.setLong(3, newTicketSeat.getPrice());
			statementInsertTicketSeat.setInt(4, newTicketSeat.getDiscountPercentage());

			statementInsertTicketSeat.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
