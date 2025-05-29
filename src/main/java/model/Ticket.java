package model;

import java.sql.Timestamp;

public class Ticket {
	
	private Long ticketId;
	private Long userId;
	private Long movieId;
	private Long theaterId;
	private Long totalAmount;
	private Timestamp createdAt;
	
	public Ticket() {}
	
	public Ticket(Long ticketId, Long userId, Long movieId, Long theaterId, Long totalAmount, Timestamp createdAt) {
		this.ticketId = ticketId;
		this.userId = userId;
		this.movieId = movieId;
		this.theaterId = theaterId;
		this.totalAmount = totalAmount;
		this.createdAt = createdAt;
	}

	public Long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public Long getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Timestamp getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

}
