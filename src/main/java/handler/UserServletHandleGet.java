package handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

public class UserServletHandleGet {
	
	private UserDAO userDAO;
	
	public UserServletHandleGet() {
		this.userDAO = new UserDAO();
	}
	
	public void handleLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./login.jsp").forward(request, response);
	}

	public void handleLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().removeAttribute("loginUser");

		System.out.println("Logout success");
		response.sendRedirect("./home");
	}
	
	public void handleRegister(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./register.jsp").forward(request, response);
	}

	public void handleGetUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> userList = this.userDAO.getUsers();
		request.setAttribute("userList", userList);

		request.getRequestDispatcher("./table-user.jsp").forward(request, response);
	}
	
	public void handleGetUserDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		User foundUser = this.userDAO.getUserById(id);
		request.setAttribute("foundUser", foundUser);

		request.getRequestDispatcher("./modify-user.jsp").forward(request, response);
	}
	
	public void handleCreateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getUsers");
	}
	
	public void handleUpdateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getUsers");
	}
	
	public void handleDeleteUser(HttpServletRequest request, HttpServletResponse response)
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
	
	public void handleMyAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./my-account.jsp").forward(request, response);
	}
	
	public void handleUpdateMyAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./myAccount");
	}
	
	public void handleRetrievePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("./retrieve-password.jsp").forward(request, response);
	}

}
