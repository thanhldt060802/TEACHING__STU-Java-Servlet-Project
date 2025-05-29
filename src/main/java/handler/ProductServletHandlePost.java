package handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

public class ProductServletHandlePost {
	
	private ProductDAO productDAO;
	
	public ProductServletHandlePost() {
		this.productDAO = new ProductDAO();
	}
	
	public void handleCreateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("nameInput");
		String image = request.getParameter("iamgeInput");
		Long price = Long.parseLong(request.getParameter("price"));
		Integer discountPercentage = Integer.parseInt(request.getParameter("discount_percentage"));
		Integer stock = Integer.parseInt(request.getParameter("stock"));
		
		Product newProduct = new Product();
		newProduct.setName(name);
		newProduct.setImage(image);
		newProduct.setPrice(price);
		newProduct.setDiscountPercentage(discountPercentage);
		newProduct.setStock(stock);
		if(!this.productDAO.createProduct(newProduct, null)) {
			System.out.println("Create product failed");
			response.sendRedirect("./getProducts");
			return;
		}
		
		System.out.println("Create product successful");
		response.sendRedirect("./getProducts");
	}
	
	public void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idInput"));
		String name = request.getParameter("nameInput");
		String image = request.getParameter("image");
		Long price = Long.parseLong(request.getParameter("price"));
		Integer discountPercentage = Integer.parseInt(request.getParameter("discount_percentage"));
		Integer stock = Integer.parseInt(request.getParameter("stock"));
		
		Product foundProduct = this.productDAO.getProductById(id);
		foundProduct.setName(name);
		foundProduct.setImage(image);
		foundProduct.setPrice(price);
		foundProduct.setDiscountPercentage(discountPercentage);
		foundProduct.setStock(stock);
		
		if(!this.productDAO.updateProduct(foundProduct, null)) {
			System.out.println("Update product failed");
			response.sendRedirect("./getProductDetail?id=" + id);
			return;
		}
		
		System.out.println("Update product successful");
		response.sendRedirect("./getProductDetail?id=" + id);
	}

}
