package handler;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TheaterDAO;
import model.Theater;

public class TheaterServletHandlePost {
	
private TheaterDAO theaterDAO;
	
	public TheaterServletHandlePost() {
		this.theaterDAO = new TheaterDAO();
	}
	
	public void handleCreateTheater(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("nameInput");
		String location = request.getParameter("locationInput");
		
		Theater newTheater = new Theater();
		newTheater.setName(name);
		newTheater.setLocation(location);
		if(!this.theaterDAO.createTheater(newTheater)) {
			System.out.println("Create theater failed");
			response.sendRedirect("./getTheaters");
			return;
		}
		
		System.out.println("Create theater successful");
		response.sendRedirect("./getTheaters");
	}
	
	public void handleUpdateTheater(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idInput"));
		String name = request.getParameter("nameInput");
		String location = request.getParameter("locationInput");
		
		Theater foundTheater = this.theaterDAO.getTheaterById(id);
		foundTheater.setName(name);
		foundTheater.setLocation(location);
		
		if(!this.theaterDAO.updateTheater(foundTheater)) {
			System.out.println("Update theater failed");
			response.sendRedirect("./getTheaterDetail?id=" + id);
			return;
		}
		
		System.out.println("Update theater successful");
		response.sendRedirect("./getTheaterDetail?id=" + id);
	}

}
