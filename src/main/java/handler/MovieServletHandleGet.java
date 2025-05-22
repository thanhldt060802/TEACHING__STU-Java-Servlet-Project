package handler;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import dao.ShowDAO;
import dao.TheaterDAO;
import model.Movie;
import model.Show;
import model.Theater;
import model.User;

public class MovieServletHandleGet {
	
	private MovieDAO movieDAO;
	private ShowDAO showDAO;
	private TheaterDAO theaterDAO;
	
	public MovieServletHandleGet() {
		this.movieDAO = new MovieDAO();
		this.showDAO = new ShowDAO();
		this.theaterDAO = new TheaterDAO();
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
			Map<Long, List<Show>> theaterIdMap = this.showDAO.getShowsByMovieId(id).stream()
					.collect(Collectors.groupingBy((show) -> {
						return show.getTheaterId();
					}));
			
			Map<Theater, List<Show>> theaterMap = new HashMap<Theater, List<Show>>();
			theaterIdMap.forEach((theaterId, showList) -> {
				theaterMap.put(this.theaterDAO.getTheaterById(theaterId), showList);
			});
			
			request.setAttribute("theaterMap", theaterMap);
			request.getRequestDispatcher("./movie-detail.jsp").forward(request, response);
		}
	}
	
	public void handleCreateMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getMovies");
	}
	
	public void handleUpdateMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getMovies");
	}
	
	public void handleDeleteMovie(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		if(!this.movieDAO.deleteMovie(id)) {
			System.out.println("Delete movie failed");
			response.sendRedirect("./getMovies");
			return;
		}
		
		System.out.println("Delete user successful");
		response.sendRedirect("./getMovies");
	}

}
