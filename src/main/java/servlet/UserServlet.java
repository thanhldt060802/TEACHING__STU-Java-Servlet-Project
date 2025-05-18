package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.UserServletHandleGet;
import handler.UserServletHandlePost;


@WebServlet({ "/login", "/logout", "/register", "/getUsers", "/getUserDetail", "/createUser", "/updateUser", "/deleteUser", "/myAccount", "/updateMyAccount", "/retrievePassword" })
public class UserServlet extends HttpServlet {

	private UserServletHandleGet handleGet;
	private UserServletHandlePost handlePost;
	
	private static final String LOGIN_PATTERN = "/login";  // GET & POST
	private static final String LOGOUT_PATTERN = "/logout";  // GET
	private static final String REGISTER_PATTERN = "/register";  // GET & POST
	private static final String GET_USERS_PATTERN = "/getUsers";  // GET
	private static final String GET_USER_DETAIL_PATTERN = "/getUserDetail";  // GET
	private static final String CREATE_USER_PATTERN = "/createUser";  // GET & POST
	private static final String UPDATE_USER_PATTERN = "/updateUser";  // GET & POST
	private static final String DELETE_USER_PATTERN = "/deleteUser";  // GET
	private static final String MY_ACCOUNT_PATTERN = "/myAccount";  // GET
	private static final String UPDATE_MY_ACCOUNT_PATTERN = "/updateMyAccount";  // GET & POST
	private static final String RETRIEVE_PASSWORD = "/retrievePassword";  // GET & POST
	
	public UserServlet() {
		this.handleGet = new UserServletHandleGet();
		this.handlePost = new UserServletHandlePost();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case LOGIN_PATTERN:
			this.handleGet.handleLogin(request, response);
			break;

		case LOGOUT_PATTERN:
			this.handleGet.handleLogout(request, response);
			break;
			
		case REGISTER_PATTERN:
			this.handleGet.handleRegister(request, response);
			break;

		case GET_USERS_PATTERN:
			this.handleGet.handleGetUsers(request, response);
			break;
			
		case GET_USER_DETAIL_PATTERN:
			this.handleGet.handleGetUserDetail(request, response);
			break;
			
		case CREATE_USER_PATTERN:
			this.handleGet.handleCreateUser(request, response);
			break;
			
		case UPDATE_USER_PATTERN:
			this.handleGet.handleUpdateUser(request, response);
			break;
			
		case DELETE_USER_PATTERN:
			this.handleGet.handleDeleteUser(request, response);
			break;
			
		case MY_ACCOUNT_PATTERN:
			this.handleGet.handleMyAccount(request, response);
			break;
			
		case UPDATE_MY_ACCOUNT_PATTERN:
			this.handleGet.handleUpdateMyAccount(request, response);
			break;
			
		case RETRIEVE_PASSWORD:
			this.handleGet.handleRetrievePassword(request, response);
			break;

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case LOGIN_PATTERN:
			this.handlePost.handleLogin(request, response);
			break;
			
		case REGISTER_PATTERN:
			this.handlePost.handleRegister(request, response);
			break;
			
		case CREATE_USER_PATTERN:
			this.handlePost.handleCreateUser(request, response);
			break;
			
		case UPDATE_USER_PATTERN:
			this.handlePost.handleUpdateUser(request, response);
			break;
			
		case UPDATE_MY_ACCOUNT_PATTERN:
			this.handlePost.handleUpdateMyAccount(request, response);
			break;
			
		case RETRIEVE_PASSWORD:
			this.handlePost.handleRetrievePassword(request, response);
			break;
			
		}
	}

	

}
