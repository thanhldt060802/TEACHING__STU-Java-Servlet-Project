package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import infrastructure.MySQLDB;
import model.Product;

public class ProductDAO {
	
	public List<Product> getProducts() {
		List<Product> products = new ArrayList<Product>();
		String sqlGetAllProducts = "SELECT * FROM products";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetAllProducts = connection.prepareStatement(sqlGetAllProducts);
			
			ResultSet rsGetAllProducts = statementGetAllProducts.executeQuery();
			while (rsGetAllProducts.next()) {
				Product product = new Product();
				product.setProductId(rsGetAllProducts.getLong("product_id"));
				product.setName(rsGetAllProducts.getString("name"));
				product.setImage(rsGetAllProducts.getString("image"));
				product.setPrice(rsGetAllProducts.getLong("price"));
				product.setDiscountPercentage(rsGetAllProducts.getInt("discount_percentage"));
				product.setStock(rsGetAllProducts.getInt("stock"));
				products.add(product);
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
		return products;
	}
	
	public Product getProductById(Long id) {
		Product foundProduct = null;
		String sqlGetProductById = "SELECT * FROM products WHERE product_id = ?";
		Connection connection = MySQLDB.getConnection();
		try {
			PreparedStatement statementGetProductById = connection.prepareStatement(sqlGetProductById);
			statementGetProductById.setLong(1, id);
			
			ResultSet rsGetProductById = statementGetProductById.executeQuery();
			if (rsGetProductById.next()) {
				foundProduct = new Product();
				foundProduct.setProductId(rsGetProductById.getLong("product_id"));
				foundProduct.setName(rsGetProductById.getString("name"));
				foundProduct.setImage(rsGetProductById.getString("image"));
				foundProduct.setPrice(rsGetProductById.getLong("price"));
				foundProduct.setDiscountPercentage(rsGetProductById.getInt("discount_percentage"));
				foundProduct.setStock(rsGetProductById.getInt("stock"));
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
		return foundProduct;
	}
			
	public boolean createProduct(Product newProduct, Connection refConnection) {
		String sqlInsertProduct = "INSERT INTO products(name, image, price, discount_percentage, stock) VALUES (?, ?, ?, ?, ?)";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
		try {
			PreparedStatement statementInsertProduct = connection.prepareStatement(sqlInsertProduct);
			statementInsertProduct.setString(1, newProduct.getName());
			statementInsertProduct.setString(2, newProduct.getImage());
			statementInsertProduct.setLong(3, newProduct.getPrice());
			statementInsertProduct.setInt(4, newProduct.getDiscountPercentage());
			statementInsertProduct.setInt(5, newProduct.getStock());
			statementInsertProduct.executeUpdate();
			
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
	
	public boolean updateProduct(Product updatedProduct, Connection refConnection) {
		String sqlUpdateProduct = "UPDATE products SET name = ?, image = ?, price = ?, discount_percentage = ?, stock= ? WHERE product_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
		try {
            PreparedStatement statementUpdateProduct = connection.prepareStatement(sqlUpdateProduct);
            statementUpdateProduct.setString(1, updatedProduct.getName());
            statementUpdateProduct.setString(2, updatedProduct.getImage());
            statementUpdateProduct.setLong(3, updatedProduct.getPrice());
            statementUpdateProduct.setInt(4, updatedProduct.getDiscountPercentage());
            statementUpdateProduct.setInt(5, updatedProduct.getStock());
            statementUpdateProduct.setLong(6, updatedProduct.getProductId());
            statementUpdateProduct.executeUpdate();
            
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
	
	public boolean deleteProduct(Long id, Connection refConnection) {
		String sqlDeleteProduct = "DELETE FROM products WHERE product_id = ?";
		Connection connection = refConnection;
		if(connection == null) {
			connection = MySQLDB.getConnection();
		}
        try {        	
        	// Xoá tất cả những dữ liệu liên quan Product trước khi xoá Product vì Database liên kết
        	
            PreparedStatement statementDeleteProduct = connection.prepareStatement(sqlDeleteProduct);
            statementDeleteProduct.setLong(1, id);
            statementDeleteProduct.executeUpdate();
            
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
