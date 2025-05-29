package handler;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
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
		if(!this.userDAO.createUser(newUser, null)) {
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
		if(!this.userDAO.createUser(newUser, null)) {
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
		
		if(!this.userDAO.updateUser(foundUser, null)) {
			System.out.println("Update user failed");
			response.sendRedirect("./getUserDetail?id=" + id);
			return;
		}
		
		System.out.println("Update user successful");
		response.sendRedirect("./getUserDetail?id=" + id);
	}
	
	public void handleUpdateMyAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String fullName = request.getParameter("fullNameInput");
		String email = request.getParameter("emailInput");
		String password = request.getParameter("newPasswordInput");
		
		User loginUser = (User)request.getSession().getAttribute("loginUser");
		
		loginUser.setFullName(fullName);
		if(!loginUser.getEmail().equals(email)) {
			if(this.userDAO.getUserByEmail(email) != null) {
				System.out.println("Email of user is already exists");
				response.sendRedirect("./myAccount");
				return;
			}else {
				loginUser.setEmail(email);
			}
		}
		if(!password.equals("")) {
			loginUser.setPassword(password);
		}
		
		if(!this.userDAO.updateUser(loginUser, null)) {
			System.out.println("Update account failed");
			response.sendRedirect("./myAccount");
			return;
		}
		
		System.out.println("Update account successful");
		response.sendRedirect("./myAccount");
	}
	
	public void handleRetrievePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String username = request.getParameter("usernameInput");
		String email = request.getParameter("emailInput");
		
		User foundUser = this.userDAO.getUserByUsername(username);
		
		if(!foundUser.getEmail().equals(email)) {
			System.out.println("Email of account do not match");
			response.sendRedirect("./retrievePassword");
			return;
		}
		
		foundUser.setPassword("123");
		if(!this.userDAO.updateUser(foundUser, null)) {
			System.out.println("Reset password failed");
			response.sendRedirect("./retrievePassword");
			return;
		}
		
		// Send Email
		String fromEmail = "from@gmail.com";
		String password = "app_pass";
		String toEmail = foundUser.getEmail();
		String subject = "RETRIEVE PASSWORD";
		String body = "Your new passowrd: " + foundUser.getPassword();;
		this.sendEmail(fromEmail, password, toEmail, subject, body);
		
		System.out.println("Check your email");
		response.sendRedirect("./retrievePassword");
	}
	
	private void sendEmail(String fromEmail, String password, String toEmail, String subject, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, authenticator);
		
		try {
			// Tạo message
			Message message = new MimeMessage(session);
			
			// Đặt người gửi
			message.setFrom(new InternetAddress(fromEmail));
			
			// Đặt người nhận
			message.setRecipient(RecipientType.TO, new InternetAddress(toEmail));
			
			// Đặt tiều đề
			message.setSubject(subject);
			
			// Đặt nội dung
			message.setText(body);
			
			// Gửi mail
			Transport.send(message);
			
			System.out.println("Send email successful");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
