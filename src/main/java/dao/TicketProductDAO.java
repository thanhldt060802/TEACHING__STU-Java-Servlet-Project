package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import infrastructure.MySQLDB;
import model.TicketProduct;

public class TicketProductDAO {
	
	public boolean createTicketProduct(TicketProduct newTicketProduct) {
		String sqlInsertTicketProduct = "INSERT INTO ticket_products(ticket_id, product_id, price, discount, quantity, total_price) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			Connection connection = MySQLDB.getConnection();
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
		}
		return false;
	}

}
