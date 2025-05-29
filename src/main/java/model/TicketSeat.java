package model;

public class TicketSeat {
	
	private Long ticketSeatId;
	private Long ticketId;
	private Long seatId;
	private Long price;
	private Integer discountPercentage;
	private Long totalPrice;
	
	public TicketSeat() {}

	public TicketSeat(Long ticketSeatId, Long ticketId, Long seatId, Long price, Integer discountPercentage, Long totalPrice) {
		this.ticketSeatId = ticketSeatId;
		this.ticketId = ticketId;
		this.seatId = seatId;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.totalPrice = totalPrice;
	}

	public Long getTicketSeatId() {
		return this.ticketSeatId;
	}

	public void setTicketSeatId(Long ticketSeatId) {
		this.ticketSeatId = ticketSeatId;
	}

	public Long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getSeatId() {
		return this.seatId;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
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
	
	public Long getTotalPrice() {
		return this.totalPrice;
	}
	
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
}
