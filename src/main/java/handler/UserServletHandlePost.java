package handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

public class UserServletHandlePost {
	
	private UserDAO userDAO;
	
	public UserServletHandlePost() {
		this.userDAO = new UserDAO();
	}
	
	public void handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("usernameInput");
		String password = request.getParameter("passwordInput");
		
		User loginUser = this.userDAO.getUserByUsername(username);
		if (loginUser == null) {
			System.out.println("Username of user is not valid");
			response.sendRedirect("./login");
			return;
		}

		if (!password.equals(loginUser.getPassword())) {
			System.out.println("Password of user does not match");
			response.sendRedirect("./login");
			return;
		}

		request.getSession().setAttribute("loginUser", loginUser);

		System.out.println("Login success");
		response.sendRedirect("./home");
	}
	
	public void handleRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullNameInput");
		String email = request.getParameter("emailInput");
		String username = request.getParameter("usernameInput");
		String password = request.getParameter("passwordInput");
		
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
		newUser.setPassword(password);
		newUser.setRoleName("CUSTOMER");
		if(!this.userDAO.createUser(newUser)) {
			System.out.println("Register failed");
			response.sendRedirect("./register");
			return;
		}
		
		System.out.println("Register success");
		response.sendRedirect("./login");
	}

	public void handleCreateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullNameInput");
		String email = request.getParameter("emailInput");
		String username = request.getParameter("usernameInput");
		String password = request.getParameter("passwordInput");
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
		newUser.setPassword(password);
		newUser.setRoleName(roleName);
		if(!this.userDAO.createUser(newUser)) {
			System.out.println("Create user failed");
			response.sendRedirect("./getUsers");
			return;
		}
		
		System.out.println("Create user successful");
		response.sendRedirect("./getUsers");
	}

	public void handleUpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idInput"));
		String fullName = request.getParameter("fullNameInput");
		String email = request.getParameter("emailInput");
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
