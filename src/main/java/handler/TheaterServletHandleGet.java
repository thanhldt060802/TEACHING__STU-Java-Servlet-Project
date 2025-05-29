package handler;

import java.io.IOException;
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

public class TheaterServletHandleGet {

	private TheaterDAO theaterDAO;
	private ShowDAO showDAO;
	private MovieDAO movieDAO;
	
	public TheaterServletHandleGet() {
		this.theaterDAO = new TheaterDAO();
		this.showDAO = new ShowDAO();
		this.movieDAO = new MovieDAO();
	}
	
	public void handleGetTheaters(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Theater> theaterList = this.theaterDAO.getTheaters();
		request.setAttribute("theaterList", theaterList);

		User loginUser = (User)request.getSession().getAttribute("loginUser");
		
		if(loginUser != null && loginUser.getRoleName().equals("ADMIN")) {
			request.getRequestDispatcher("./table-theater.jsp").forward(request, response);
		}else {
			request.getRequestDispatcher("./theaters.jsp").forward(request, response);
		}
	}
	
	public void handleGetTheaterDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		Theater foundTheater = this.theaterDAO.getTheaterById(id);
		request.setAttribute("foundTheater", foundTheater);

		User loginUser = (User)request.getSession().getAttribute("loginUser");
		
		if(loginUser != null && loginUser.getRoleName().equals("ADMIN")) {
			request.getRequestDispatcher("./modify-theater.jsp").forward(request, response);
		}else {
			Map<Long, List<Show>> movieIdMap = this.showDAO.getShowsByTheaterId(id).stream()
					.collect(Collectors.groupingBy((show) -> {
						return show.getMovieId();
					}));
			Map<Movie, List<Show>> movieMap = new HashMap<Movie, List<Show>>();
			movieIdMap.forEach((movieId, showList) -> {
				movieMap.put(this.movieDAO.getMovieById(movieId), showList);
			});
			request.setAttribute("movieMap", movieMap);
			request.getRequestDispatcher("./theater-detail.jsp").forward(request, response);
		}
	}
	
	public void handleCreateTheater(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getTheaters");
	}
	
	public void handleUpdateTheater(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("./getTheaters");
	}
	
	public void handleDeleteTheater(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("id"));
		
		if(!this.theaterDAO.deleteTheater(id, null)) {
			System.out.println("Delete theater failed");
			response.sendRedirect("./getTheaters");
			return;
		}
		
		System.out.println("Delete user successful");
		response.sendRedirect("./getTheaters");
	}
	
}
