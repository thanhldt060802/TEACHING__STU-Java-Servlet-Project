package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet({ "/login", "/logout", "/register", "/retrivePassowrd", "/myAccount", "/updateMyAccount", "/getUsers", "/getUserDetail", "/createUser", "/updateUser", "/deleteUser" })
public class UserServlet extends HttpServlet {

	private UserDAO userDAO;

	public UserServlet() {
		this.userDAO = new UserDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case "/login":
			this.handleLoginHTTPGet(request, response);
			break;

		case "/logout":
			this.handleLogoutHTTPGet(request, response);
			break;
			
		case "/register":
			this.handleRegisterHTTPGet(request, response);
			break;

		case "/getUsers":
			this.handleGetUsersHTTPGet(request, response);
			break;
			
		case "/getUserDetail":
			this.handleGetUserDetailHTTPGet(request, response);
			break;
			
		case "/deleteUser":
			this.handleDeleteUserHTTPGet(request, response);
			break;

		}
	}

	private void handleLoginHTTPGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./login.jsp").forward(request, response);
	}

	private void handleLogoutHTTPGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("loginUser");

		System.out.println("Logout success");
		response.sendRedirect("./home");
	}
	
	private void handleRegisterHTTPGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./register.jsp").forward(request, response);
	}

	private void handleGetUsersHTTPGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = this.userDAO.getUsers();
		request.setAttribute("userList", userList);

		request.getRequestDispatcher("./table-user.jsp").forward(request, response);
	}
	
	private void handleGetUserDetailHTTPGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		User foundUser = this.userDAO.getUserById(id);
		request.setAttribute("foundUser", foundUser);

		request.getRequestDispatcher("./modify-user.jsp").forward(request, response);
	}
	
	private void handleDeleteUserHTTPGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		if(!this.userDAO.deleteUser(id)) {
			System.out.println("Delete user failed");
			response.sendRedirect("./getUserDetail?id=" + id);
			return;
		}
		
		System.out.println("Delete user successful");
		response.sendRedirect("./getUsers");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case "/login":
			this.handleLoginHTTPPost(request, response);
			break;
			
		case "/register":
			this.handleRegisterHTTPPost(request, response);
			break;

		case "/createUser":
			this.handleCreateUserHTTPPost(request, response);
			break;

		case "/updateUser":
			this.handleUpdateUserHTTPPost(request, response);
			break;

		}
	}

	private void handleLoginHTTPPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("usernameInput");
		String password = request.getParameter("passwordInput");
		
		User loginUser = this.userDAO.getUserByUsername(username);
		if (loginUser == null) {
			System.out.println("Username of user is not valid");
			response.sendRedirect("./login");
			return;
		}

		if (!password.equals(loginUser.getHashedPassword())) {
			System.out.println("Password of user does not match");
			response.sendRedirect("./login");
			return;
		}

		request.getSession().setAttribute("loginUser", loginUser);

		System.out.println("Login success");
		response.sendRedirect("./home");
	}
	
	private void handleRegisterHTTPPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullNameInput");
		String email = request.getParameter("emailInput");
		String username = request.getParameter("usernameInput");
		String password = request.getParameter("passwordInput");
		String address = request.getParameter("addressInput");
		
		if(this.userDAO.getUserByUsername(username) != null) {
			System.out.println("Username of user is already exists");
			response.sendRedirect("./register");
			return;
		}
		
		if(this.userDAO.getUserByEmail(email) != null) {
			System.out.println("Email of user is already exists");
			response.sendRedirect("./register");
			return;
		}
		
		User newUser = new User();
		newUser.setFullName(fullName);
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setHashedPassword(password);
		newUser.setAddress(address);
		newUser.setRoleName("CUSTOMER");
		if(!this.userDAO.createUser(newUser)) {
			System.out.println("Register failed");
			response.sendRedirect("./register");
			return;
		}
		
		System.out.println("Register success");
		response.sendRedirect("./login");
	}

	private void handleCreateUserHTTPPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullNameInput");
		String email = request.getParameter("emailInput");
		String username = request.getParameter("usernameInput");
		String password = request.getParameter("passwordInput");
		String address = request.getParameter("addressInput");
		String roleName = request.getParameter("roleNameInput");
		
		if(this.userDAO.getUserByUsername(username) != null) {
			System.out.println("Username of user is already exists");
			response.sendRedirect("./getUsers");
			return;
		}
		
		if(this.userDAO.getUserByEmail(email) != null) {
			System.out.println("Email of user is already exists");
			response.sendRedirect("./getUsers");
			return;
		}
		
		User newUser = new User();
		newUser.setFullName(fullName);
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setHashedPassword(password);
		newUser.setAddress(address);
		newUser.setRoleName(roleName);
		if(!this.userDAO.createUser(newUser)) {
			System.out.println("Create user failed");
			response.sendRedirect("./getUsers");
			return;
		}
		
		System.out.println("Create user successful");
		response.sendRedirect("./getUsers");
	}

	private void handleUpdateUserHTTPPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idInput"));
		String fullName = request.getParameter("fullNameInput");
		String email = request.getParameter("emailInput");
		String address = request.getParameter("addressInput");
		String roleName = request.getParameter("roleNameInput");
		
		User foundUser = this.userDAO.getUserById(id);
		foundUser.setFullName(fullName);
		if(!foundUser.getEmail().equals(email)) {
			if(this.userDAO.getUserByEmail(email) != null) {
				System.out.println("Email of user is already exists");
				response.sendRedirect("./getUserDetail?id=" + id);
				return;
			}else {
				foundUser.setEmail(email);
			}
		}
		foundUser.setAddress(address);
		foundUser.setRoleName(roleName);
		if(!this.userDAO.updateUser(foundUser)) {
			System.out.println("Update user failed");
			response.sendRedirect("./getUserDetail?id=" + id);
			return;
		}
		
		System.out.println("Update user successful");
		response.sendRedirect("./getUserDetail?id=" + id);
	}

}
