package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;

@WebServlet({ "/login", "/logout", "/register" })
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case "/login":
			this.handleLoginHTTPPost(request, response);
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
		response.sendRedirect("./product.jsp");
	}

}
