package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.ProductServletHandleGet;
import handler.ProductServletHandlePost;


@WebServlet({ "/getProducts", "/getProductDetail", "/createProduct", "/updateProduct", "/deleteProduct" })
public class ProductServlet extends HttpServlet {
	
	private ProductServletHandleGet handleGet;
	private ProductServletHandlePost handlePost;
	
	private static final String GET_PRODUCTS_PATTERN = "/getProducts";  // GET
	private static final String GET_PRODUCT_DETAIL_PATTERN = "/getProductDetail";  // GET
	private static final String CREATE_PRODUCT_PATTERN = "/createProduct";  // GET & POST
	private static final String UPDATE_PRODUCT_PATTERN = "/updateProduct";  // GET & POST
	private static final String DELETE_PRODUCT_PATTERN = "/deleteProduct";  // GET
	
    public ProductServlet() {
    	this.handleGet = new ProductServletHandleGet();
    	this.handlePost = new ProductServletHandlePost();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case GET_PRODUCTS_PATTERN:
			this.handleGet.handleGetProducts(request, response);
			break;

		case GET_PRODUCT_DETAIL_PATTERN:
			this.handleGet.handleGetProductDetail(request, response);
			break;
			
		case CREATE_PRODUCT_PATTERN:
			this.handleGet.handleCreateProduct(request, response);
			break;

		case UPDATE_PRODUCT_PATTERN:
			this.handleGet.handleUpdateProduct(request, response);
			break;
			
		case DELETE_PRODUCT_PATTERN:
			this.handleGet.handleDeleteProduct(request, response);
			break;

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {
			
		case CREATE_PRODUCT_PATTERN:
			this.handlePost.handleCreateProduct(request, response);
			break;

		case UPDATE_PRODUCT_PATTERN:
			this.handlePost.handleUpdateProduct(request, response);
			break;
			
		}
	}

}
