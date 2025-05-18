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
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetAllProducts = connection.prepareStatement(sqlGetAllProducts);
			
			ResultSet rsGetAllProducts = statementGetAllProducts.executeQuery();
			while (rsGetAllProducts.next()) {
				Product product = new Product();
				product.setProductId(rsGetAllProducts.getLong("product_id"));
				product.setName(rsGetAllProducts.getString("name"));
				product.setPrice(rsGetAllProducts.getLong("price"));
				product.setDiscountPercentage(rsGetAllProducts.getInt("discount_percentage"));
				product.setStock(rsGetAllProducts.getInt("stock"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	public Product getProductById(Long id) {
		Product foundProduct = null;
		String sqlGetProductById = "SELECT * FROM products WHERE product_id = ?";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementGetProductById = connection.prepareStatement(sqlGetProductById);
			statementGetProductById.setLong(1, id);
			
			ResultSet rsGetProductById = statementGetProductById.executeQuery();
			if (rsGetProductById.next()) {
				foundProduct = new Product();
				foundProduct.setProductId(rsGetProductById.getLong("product_id"));
				foundProduct.setName(rsGetProductById.getString("name"));
				foundProduct.setPrice(rsGetProductById.getLong("price"));
				foundProduct.setDiscountPercentage(rsGetProductById.getInt("discount_percentage"));
				foundProduct.setStock(rsGetProductById.getInt("stock"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return foundProduct;
	}
			
	public boolean createProduct(Product newProduct) {
		String sqlInsertProduct = "INSERT INTO products(name, price, discount_percentage, stock) VALUES (?, ?, ?, ?)";
		try {
			Connection connection = MySQLDB.getConnection();
			PreparedStatement statementInsertProduct = connection.prepareStatement(sqlInsertProduct);
			statementInsertProduct.setString(1, newProduct.getName());
			statementInsertProduct.setLong(2, newProduct.getPrice());
			statementInsertProduct.setInt(3, newProduct.getDiscountPercentage());
			statementInsertProduct.setInt(4, newProduct.getStock());

			statementInsertProduct.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateProduct(Product updatedProduct) {
		String sqlUpdateProduct = "UPDATE products SET name = ?, price = ?, discount_percentage = ?, stock= ? WHERE product_id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementUpdateProduct = connection.prepareStatement(sqlUpdateProduct);
            statementUpdateProduct.setString(1, updatedProduct.getName());
            statementUpdateProduct.setLong(2, updatedProduct.getPrice());
            statementUpdateProduct.setInt(3, updatedProduct.getDiscountPercentage());
            statementUpdateProduct.setInt(4, updatedProduct.getStock());
            statementUpdateProduct.setLong(5, updatedProduct.getProductId());

            statementUpdateProduct.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}
	
	public boolean deleteProduct(Long id) {
		String sqlDeleteProduct = "DELETE FROM products WHERE product_id = ?";
        try {
        	Connection connection = MySQLDB.getConnection();
            PreparedStatement statementDeleteProduct = connection.prepareStatement(sqlDeleteProduct);
            statementDeleteProduct.setLong(1, id);
            
            statementDeleteProduct.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
	}

}
