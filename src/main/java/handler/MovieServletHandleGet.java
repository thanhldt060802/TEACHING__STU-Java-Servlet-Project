package handler;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import model.Movie;
import model.User;

public class MovieServletHandleGet {
	
	private MovieDAO movieDAO;
	
	public MovieServletHandleGet() {
		this.movieDAO = new MovieDAO();
	}
	
	public void handleGetMovies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Movie> movieList = this.movieDAO.getMovies();
		request.setAttribute("movieList", movieList);

		User loginUser = (User)request.getSession().getAttribute("loginUser");
		
		if(loginUser != null && loginUser.getRoleName().equals("ADMIN")) {
			request.getRequestDispatcher("./table-movie.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("./movies.jsp").forward(request, response);
		}
	}
	
	public void handleGetMovieDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		Movie foundMovie = this.movieDAO.getMovieById(id);
		request.setAttribute("foundMovie", foundMovie);

		User loginUser = (User)request.getSession().getAttribute("loginUser");
		
		if(loginUser != null && loginUser.getRoleName().equals("ADMIN")) {
			request.getRequestDispatcher("./modify-movie.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("./movie-detail.jsp").forward(request, response);
		}
	}

}
