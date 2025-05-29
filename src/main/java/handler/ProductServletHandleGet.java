package handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Product;

public class ProductServletHandleGet {
	
	private ProductDAO productDAO;
	
	public ProductServletHandleGet() {
		this.productDAO = new ProductDAO();
	}
	
	public void handleGetProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Product> productList = this.productDAO.getProducts();
		request.setAttribute("productList", productList);
		
		request.getRequestDispatcher("./table-product.jsp").forward(request, response);
	}
	
	public void handleGetProductDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		Product foundProduct = this.productDAO.getProductById(id);
		request.setAttribute("foundProduct", foundProduct);
		
		request.getRequestDispatcher("./modify-product.jsp").forward(request, response);
	}
	
	public void handleCreateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getProducts");
	}
	
	public void handleUpdateProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getProducts");
	}
	
	public void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		if(!this.productDAO.deleteProduct(id, null)) {
			System.out.println("Delete product failed");
			response.sendRedirect("./getProducts");
			return;
		}
		
		System.out.println("Delete user successful");
		response.sendRedirect("./getProducts");
	}

}
