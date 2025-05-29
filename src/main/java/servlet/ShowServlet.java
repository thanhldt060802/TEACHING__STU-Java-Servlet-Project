package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.ShowServletHandleGet;
import handler.ShowServletHandlePost;


@WebServlet({ "/getShows", "/getShowDetail", "/createShow", "/updateShow", "/deleteShow" })
public class ShowServlet extends HttpServlet {
	
	private ShowServletHandleGet handleGet;
	private ShowServletHandlePost handlePost;
	
	private static final String GET_SHOWS_PATTERN = "/getShows";  // GET
	private static final String GET_SHOW_DETAIL_PATTERN = "/getShowDetail";  // GET
	private static final String CREATE_SHOW_PATTERN = "/createShow";  // GET & POST
	private static final String UPDATE_SHOW_PATTERN = "/updateShow";  // GET & POST
	private static final String DELETE_SHOW_PATTERN = "/deleteShow";  // GET

    public ShowServlet() {
    	this.handleGet = new ShowServletHandleGet();
    	this.handlePost = new ShowServletHandlePost();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case GET_SHOWS_PATTERN:
			this.handleGet.handleGetShows(request, response);
			break;

		case GET_SHOW_DETAIL_PATTERN:
			this.handleGet.handleGetShowDetail(request, response);
			break;
			
		case CREATE_SHOW_PATTERN:
			this.handleGet.handleCreateShow(request, response);
			break;

		case UPDATE_SHOW_PATTERN:
			this.handleGet.handleUpdateShow(request, response);
			break;
			
		case DELETE_SHOW_PATTERN:
			this.handleGet.handleDeleteShow(request, response);
			break;
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {
			
		case CREATE_SHOW_PATTERN:
			this.handlePost.handleCreateShow(request, response);
			break;

		case UPDATE_SHOW_PATTERN:
			this.handlePost.handleUpdateShow(request, response);
			break;
			
		}
	}

}
