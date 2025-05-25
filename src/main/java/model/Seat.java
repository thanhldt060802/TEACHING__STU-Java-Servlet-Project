package model;

public class Seat {
	
	private Long seatId;
	private Long showId;
	private String seatNumber;
	private Boolean available;
	
	public Seat() {}

	public Seat(Long seatId, Long showId, String seatNumber, Boolean available) {
		this.seatId = seatId;
		this.showId = showId;
		this.seatNumber = seatNumber;
		this.available = available;
	}

	public Long getSeatId() {
		return this.seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public Long getShowId() {
		return this.showId;
	}

	public void setShowId(Long showId) {
		this.showId = showId;
	}

	public String getSeatNumber() {
		return this.seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Boolean getAvailable() {
		return this.available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}
