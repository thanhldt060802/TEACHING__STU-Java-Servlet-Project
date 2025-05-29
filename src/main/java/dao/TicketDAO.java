package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import infrastructure.MySQLDB;
import model.Product;
import model.Seat;
import model.Ticket;
import model.TicketProduct;
import model.TicketSeat;

public class TicketDAO {
	
	private TicketSeatDAO ticketSeatDAO;
	private SeatDAO seatDAO;
	private TicketProductDAO ticketProductDAO;
	private ProductDAO productDAO;
	
	public TicketDAO() {
		this.ticketSeatDAO = new TicketSeatDAO();
		this.seatDAO = new SeatDAO();
		this.ticketProductDAO = new TicketProductDAO();
		this.productDAO = new ProductDAO();
	}
	
	public List<Ticket> getTickets() {
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sqlGetAllTickets = "SELECT * FROM tickets";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetAllTickets = connection.prepareStatement(sqlGetAllTickets);
			
			ResultSet rsGetAllTickets = statementGetAllTickets.executeQuery();
			while (rsGetAllTickets.next()) {
				Ticket ticket = new Ticket();
				ticket.setTicketId(rsGetAllTickets.getLong("ticket_id"));
				ticket.setUserId(rsGetAllTickets.getLong("user_id"));
				ticket.setMovieId(rsGetAllTickets.getLong("movie_id"));
				ticket.setTheaterId(rsGetAllTickets.getLong("theater_id"));
				ticket.setTotalAmount(rsGetAllTickets.getLong("total_amount"));
				ticket.setCreatedAt(rsGetAllTickets.getTimestamp("created_at"));
				tickets.add(ticket);
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
		return tickets;
	}
	
	public List<Ticket> getTicketsByUserId(Long userId) {
		List<Ticket> tickets = new ArrayList<Ticket>();
		String sqlGetAllTicketsByUserId = "SELECT * FROM tickets WHERE user_id = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetAllTicketsByUserId = connection.prepareStatement(sqlGetAllTicketsByUserId);
			statementGetAllTicketsByUserId.setLong(1, userId);
			
			ResultSet rsGetAllTicketsByUserId = statementGetAllTicketsByUserId.executeQuery();
			while (rsGetAllTicketsByUserId.next()) {
				Ticket ticket = new Ticket();
				ticket.setTicketId(rsGetAllTicketsByUserId.getLong("ticket_id"));
				ticket.setUserId(rsGetAllTicketsByUserId.getLong("user_id"));
				ticket.setMovieId(rsGetAllTicketsByUserId.getLong("movie_id"));
				ticket.setTheaterId(rsGetAllTicketsByUserId.getLong("theater_id"));
				ticket.setTotalAmount(rsGetAllTicketsByUserId.getLong("total_amount"));
				ticket.setCreatedAt(rsGetAllTicketsByUserId.getTimestamp("created_at"));
				tickets.add(ticket);
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
		return tickets;
	}
	
	public Ticket getTicketById(Long id) {
		Ticket foundTicket = null;
		String sqlGetTicketById = "SELECT * FROM tickets WHERE ticket_id = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetTicketById = connection.prepareStatement(sqlGetTicketById);
			statementGetTicketById.setLong(1, id);
			
			ResultSet rsGetTicketById = statementGetTicketById.executeQuery();
			while (rsGetTicketById.next()) {
				foundTicket = new Ticket();
				foundTicket.setTicketId(rsGetTicketById.getLong("ticket_id"));
				foundTicket.setUserId(rsGetTicketById.getLong("user_id"));
				foundTicket.setMovieId(rsGetTicketById.getLong("movie_id"));
				foundTicket.setTheaterId(rsGetTicketById.getLong("theater_id"));
				foundTicket.setTotalAmount(rsGetTicketById.getLong("total_amount"));
				foundTicket.setCreatedAt(rsGetTicketById.getTimestamp("created_at"));
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
		return foundTicket;
	}
	
	public boolean createTicket(Ticket newTicket, Map<TicketSeat, Seat> ticketSeatMap, Map<TicketProduct, Product> ticketProductMap, Connection refConnection) {
		String sqlInsertTicket = "INSERT INTO tickets (ticket_id, user_id, movie_id, theater_id, total_amount, created_at) VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
		try {
			connection.setAutoCommit(false);
			
			PreparedStatement statementInsertTicket = connection.prepareStatement(sqlInsertTicket);
			statementInsertTicket.setLong(1, newTicket.getTicketId());
			statementInsertTicket.setLong(2, newTicket.getUserId());
			statementInsertTicket.setLong(3, newTicket.getMovieId());
			statementInsertTicket.setLong(4, newTicket.getTheaterId());
			statementInsertTicket.setLong(5, newTicket.getTotalAmount());
			statementInsertTicket.setTimestamp(6, newTicket.getCreatedAt());
			statementInsertTicket.executeUpdate();
			
			for(Entry<TicketSeat, Seat> entry : ticketSeatMap.entrySet()) {
				if(!this.ticketSeatDAO.createTicketSeat(entry.getKey(), connection)) {
					connection.rollback();
					return false;
				}
				
				if(!this.seatDAO.updateSeat(entry.getValue(), connection)) {
					connection.rollback();
					return false;
				}
			}
			
			for(Entry<TicketProduct, Product> entry : ticketProductMap.entrySet()) {
				if(!this.ticketProductDAO.createTicketProduct(entry.getKey(), connection)) {
					connection.rollback();
					return false;
				}
				
				if(!this.productDAO.updateProduct(entry.getValue(), connection)) {
					connection.rollback();
					return false;
				}
			}
			
			connection.commit();
			return true;
		} catch (SQLException e1) {
			e1.printStackTrace();
			try {
				connection.rollback();
	        } catch (SQLException e2) {
	            e2.printStackTrace();
	        }
		} finally {
			if(refConnection == null) {
				try {
					connection.setAutoCommit(true);
	                connection.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
		}
		return false;
	}
	
	public boolean deleteTicket(Long id, Connection refConnection) {
		String sqlDeleteTicket = "DELETE FROM tickets WHERE ticket_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
        try {
        	// Xoá tất cả những dữ liệu liên quan Ticket trước khi xoá Ticket vì Database liên kết
        	
            PreparedStatement statementDeleteTicket = connection.prepareStatement(sqlDeleteTicket);
            statementDeleteTicket.setLong(1, id);
            statementDeleteTicket.executeUpdate();
            
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
