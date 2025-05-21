package model;

import java.sql.Timestamp;

public class Show {

	private Long showId;
	private Long movieId;
	private Long theaterId;
	private Timestamp startAt;
	
	public Show() {}

	public Show(Long showId, Long movieId, Long theaterId, Timestamp startAt) {
		this.showId = showId;
		this.movieId = movieId;
		this.theaterId = theaterId;
		this.startAt = startAt;
	}

	public Long getShowId() {
		return this.showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public Long getMovieId() {
		return this.movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getTheaterId() {
		return this.theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

	public Timestamp getStartAt() {
		return this.startAt;
	}

	public void setStartAt(Timestamp startAt) {
		this.startAt = startAt;
	}
	
}
