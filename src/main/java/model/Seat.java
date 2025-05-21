package model;

public class Seat {
	
	private Long seatId;
	private Long showId;
	private String seatNumber;
	private Long price;
	private Integer discountPercentage;
	private Boolean available;
	
	public Seat() {}

	public Seat(Long seatId, Long showId, String seatNumber, Long price, Integer discountPercentage,
			Boolean available) {
		this.seatId = seatId;
		this.showId = showId;
		this.seatNumber = seatNumber;
		this.price = price;
		this.discountPercentage = discountPercentage;
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

	public Boolean getAvailable() {
		return this.available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

}
