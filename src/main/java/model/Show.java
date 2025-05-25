package model;

import java.sql.Timestamp;

public class Show {

	private Long showId;
	private Long movieId;
	private Long theaterId;
	private Timestamp startAt;
	private Long price;
	private Integer discountPercentage;
	
	public Show() {}

	public Show(Long showId, Long movieId, Long theaterId, Timestamp startAt, Long price, Integer discountPercentage) {
		this.showId = showId;
		this.movieId = movieId;
		this.theaterId = theaterId;
		this.startAt = startAt;
		this.price = price;
		this.discountPercentage = discountPercentage;
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
	
	public Long getPrice() {
		return this.price;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Integer getDiscountPercentage() {
		return this.discountPercentage;
	}
	
	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
}
