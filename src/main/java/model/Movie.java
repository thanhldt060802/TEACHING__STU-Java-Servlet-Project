package model;

import java.sql.Timestamp;

public class Movie {
	
	private Long movieId;
	private String title;
	private String image;
	private String genre;
	private Integer duration;
	private Timestamp releaseDateAt;
	
	public Movie() {}

	public Movie(Long movieId, String title, String image, String genre, Integer duration, Timestamp releaseDateAt) {
		this.movieId = movieId;
		this.title = title;
		this.image = image;
		this.genre = genre;
		this.duration = duration;
		this.releaseDateAt = releaseDateAt;
	}

	public Long getMovieId() {
		return this.movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getGenre() {
		return this.genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Integer getDuration() {
		return this.duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Timestamp getReleaseDateAt() {
		return this.releaseDateAt;
	}

	public void setReleaseDateAt(Timestamp releaseDateAt) {
		this.releaseDateAt = releaseDateAt;
	}

}
