package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import handler.MovieServletHandleGet;
import handler.MovieServletHandlePost;


@WebServlet({ "/getMovies", "/getMovieDetail", "/createMovie", "/updateMovie", "/deleteMovie" })
public class MovieServlet extends HttpServlet {
	
	private MovieServletHandleGet handleGet;
	private MovieServletHandlePost handlePost;
	
	private static final String GET_MOVIES_PATTERN = "/getMovies";  // GET
	private static final String GET_MOVIE_DETAIL_PATTERN = "/getMovieDetail";  // GET
	private static final String CREATE_MOVIE_PATTERN = "/createMovie";  // GET & POST
	private static final String UPDATE_MOVIE_PATTERN = "/updateMovie";  // GET & POST
	private static final String DELETE_MOVIE_PATTERN = "/deleteMovie";  // GET
	
    public MovieServlet() {
    	this.handleGet = new MovieServletHandleGet();
    	this.handlePost = new MovieServletHandlePost();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {

		case GET_MOVIES_PATTERN:
			// Implementation ...
			break;

		case GET_MOVIE_DETAIL_PATTERN:
			// Implementation ...
			break;
			
		case CREATE_MOVIE_PATTERN:
			// Implementation ...
			break;

		case UPDATE_MOVIE_PATTERN:
			// Implementation ...
			break;
			
		case DELETE_MOVIE_PATTERN:
			// Implementation ...
			break;

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String route = request.getServletPath();

		switch (route) {
			
		case CREATE_MOVIE_PATTERN:
			// Implementation ...
			break;

		case UPDATE_MOVIE_PATTERN:
			// Implementation ...
			break;
			
		}
	}

}
