package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infrastructure.MySQLDB;
import model.TicketProduct;

public class TicketProductDAO {

	public List<TicketProduct> getAllTicketProductsByTicketId(Long ticketId) {
		List<TicketProduct> ticketProducts = new ArrayList<TicketProduct>();
		String sqlGetAllTicketProductsByTicketId = "SELECT * FROM ticket_products WHERE ticket_id = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetAllTicketProductsByTicketId = connection.prepareStatement(sqlGetAllTicketProductsByTicketId);
			statementGetAllTicketProductsByTicketId.setLong(1, ticketId);
			
			ResultSet rsGetAllTicketProductsByTicketId = statementGetAllTicketProductsByTicketId.executeQuery();
			while (rsGetAllTicketProductsByTicketId.next()) {
				TicketProduct ticketProduct = new TicketProduct();
				ticketProduct.setTicketProductId(rsGetAllTicketProductsByTicketId.getLong("ticket_product_id"));
				ticketProduct.setTicketId(rsGetAllTicketProductsByTicketId.getLong("ticket_id"));
				ticketProduct.setProductId(rsGetAllTicketProductsByTicketId.getLong("product_id"));
				ticketProduct.setPrice(rsGetAllTicketProductsByTicketId.getLong("price"));
				ticketProduct.setDiscountPercentage(rsGetAllTicketProductsByTicketId.getInt("discount_percentage"));
				ticketProduct.setQuantity(rsGetAllTicketProductsByTicketId.getInt("quantity"));
				ticketProduct.setTotalPrice(rsGetAllTicketProductsByTicketId.getLong("total_price"));
				ticketProducts.add(ticketProduct);
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
		return ticketProducts;
	}
	
	public boolean createTicketProduct(TicketProduct newTicketProduct, Connection refConnection) {
		String sqlInsertTicketProduct = "INSERT INTO ticket_products (ticket_id, product_id, price, discount_percentage, quantity, total_price) VALUES (?, ?, ?, ?, ?, ?)";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
		try {
			PreparedStatement statementInsertTicketProduct = connection.prepareStatement(sqlInsertTicketProduct);
			statementInsertTicketProduct.setLong(1, newTicketProduct.getTicketId());
			statementInsertTicketProduct.setLong(2, newTicketProduct.getProductId());
			statementInsertTicketProduct.setLong(3, newTicketProduct.getPrice());
			statementInsertTicketProduct.setInt(4, newTicketProduct.getDiscountPercentage());
			statementInsertTicketProduct.setInt(5, newTicketProduct.getQuantity());
			statementInsertTicketProduct.setLong(6, newTicketProduct.getTotalPrice());
			statementInsertTicketProduct.executeUpdate();
			
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
	
	public boolean deleteTicketProduct(Long id, Connection refConnection) {
		String sqlDeleteTicketProduct = "DELETE FROM ticket_products WHERE ticket_product_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
        try {
        	// Xoá tất cả những dữ liệu liên quan TicketProduct trước khi xoá TicketProduct vì Database liên kết
        	
            PreparedStatement statementDeleteTicketProduct = connection.prepareStatement(sqlDeleteTicketProduct);
            statementDeleteTicketProduct.setLong(1, id);
            statementDeleteTicketProduct.executeUpdate();
            
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
