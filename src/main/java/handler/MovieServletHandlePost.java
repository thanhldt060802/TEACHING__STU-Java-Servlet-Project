package handler;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MovieDAO;
import model.Movie;
import model.User;

public class MovieServletHandlePost {
	
	private MovieDAO movieDAO;
	
	public MovieServletHandlePost() {
		this.movieDAO = new MovieDAO();
	}
	
	public void handleUpdateMoive(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long id = Long.parseLong(request.getParameter("idInput"));
		String title = request.getParameter("titleInput");
		String image = request.getParameter("imageInput");
		String genre = request.getParameter("genreInput");
		Integer duration = Integer.parseInt(request.getParameter("durationInput"));
		Timestamp releaseDateAt = null;
		try {
			releaseDateAt = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("releaseDateAtInput")).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Movie foundMovie = this.movieDAO.getMovieById(id);
		foundMovie.setTitle(title);
		foundMovie.setImage(image);
		foundMovie.setGenre(genre);
		foundMovie.setDuration(duration);
		foundMovie.setReleaseDateAt(releaseDateAt);
		
		if(!this.movieDAO.updateMovie(foundMovie)) {
			System.out.println("Update movie failed");
			response.sendRedirect("./getMovieDetail?id=" + id);
			return;
		}
		
		System.out.println("Update movie successful");
		response.sendRedirect("./getMovieDetail?id=" + id);
	}

}
