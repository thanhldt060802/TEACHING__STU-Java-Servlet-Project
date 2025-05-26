package model;

import java.sql.Timestamp;

public class Ticket {
	
	private Long ticketId;
	private Long userId;
	private Long movieId;
	private Long theaterId;
	private Long totalAmount;
	private Timestamp createAt;
	
	public Ticket() {}
	
	public Ticket(Long ticketId, Long userId, Long movieId, Long theaterId, Long totalAmount, Timestamp createAt) {
		this.ticketId = ticketId;
		this.userId = userId;
		this.movieId = movieId;
		this.theaterId = theaterId;
		this.totalAmount = totalAmount;
		this.createAt = createAt;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMovieId() {
		return movieId;
	}

	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}

	public Long getTheaterId() {
		return theaterId;
	}

	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Timestamp getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Timestamp createAt) {
		this.createAt = createAt;
	}

}
