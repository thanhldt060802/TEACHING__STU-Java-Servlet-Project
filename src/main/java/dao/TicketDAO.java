package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import infrastructure.MySQLDB;
import model.Ticket;

public class TicketDAO {
	
	public boolean createTicket(Ticket newTicket) {
		String sqlInsertTicket = "INSERT INTO tickets(ticket_id, user_id, movie_id, theater_id, total_amount, create_at) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementInsertTicket = connection.prepareStatement(sqlInsertTicket);
			statementInsertTicket.setLong(1, newTicket.getTicketId());
			statementInsertTicket.setLong(2, newTicket.getUserId());
			statementInsertTicket.setLong(3, newTicket.getMovieId());
			statementInsertTicket.setLong(4, newTicket.getTheaterId());
			statementInsertTicket.setLong(5, newTicket.getTotalAmount());
			statementInsertTicket.setTimestamp(6, newTicket.getCreateAt());

			statementInsertTicket.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
