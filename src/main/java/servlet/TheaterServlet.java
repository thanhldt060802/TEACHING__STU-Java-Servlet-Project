package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.TheaterServletHandleGet;
import handler.TheaterServletHandlePost;


@WebServlet({ "/getTheaters", "/getTheaterDetail", "/createTheater", "/updateTheater", "/deleteTheater" })
public class TheaterServlet extends HttpServlet {
	
	private TheaterServletHandleGet handleGet;
	private TheaterServletHandlePost handlePost;
	
	private static final String GET_THEATERS_PATTERN = "/getTheaters";  // GET
	private static final String GET_THEATER_DETAIL_PATTERN = "/getTheaterDetail";  // GET
	private static final String CREATE_THEATER_PATTERN = "/createTheater";  // GET & POST
	private static final String UPDATE_THEATER_PATTERN = "/updateTheater";  // GET & POST
	private static final String DELETE_THEATER_PATTERN = "/deleteTheater";  // GET
	
    public TheaterServlet() {
    	this.handleGet = new TheaterServletHandleGet();
    	this.handlePost = new TheaterServletHandlePost();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case GET_THEATERS_PATTERN:
			this.handleGet.handleGetTheaters(request, response);
			break;

		case GET_THEATER_DETAIL_PATTERN:
			this.handleGet.handleGetTheaterDetail(request, response);
			break;
			
		case CREATE_THEATER_PATTERN:
			this.handleGet.handleCreateTheater(request, response);
			break;

		case UPDATE_THEATER_PATTERN:
			this.handleGet.handleUpdateTheater(request, response);
			break;
			
		case DELETE_THEATER_PATTERN:
			this.handleGet.handleDeleteTheater(request, response);
			break;

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {
			
		case CREATE_THEATER_PATTERN:
			this.handlePost.handleCreateUser(request, response);
			break;

		case UPDATE_THEATER_PATTERN:
			this.handlePost.handleUpdateMoive(request, response);
			break;
			
		}
	}

}
