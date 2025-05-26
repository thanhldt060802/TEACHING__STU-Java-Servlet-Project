package model;

public class TicketSeat {
	
	private Long ticketSeatId;
	private Long ticketId;
	private Long seatId;
	private Long price;
	private Integer discountPercentage;
	
	public TicketSeat() {}

	public TicketSeat(Long ticketSeatId, Long ticketId, Long seatId, Long price, Integer discountPercentage) {
		this.ticketSeatId = ticketSeatId;
		this.ticketId = ticketId;
		this.seatId = seatId;
		this.price = price;
		this.discountPercentage = discountPercentage;
	}

	public Long getTicketSeatId() {
		return ticketSeatId;
	}

	public void setTicketSeatId(Long ticketSeatId) {
		this.ticketSeatId = ticketSeatId;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getSeatId() {
		return seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Integer discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
}
