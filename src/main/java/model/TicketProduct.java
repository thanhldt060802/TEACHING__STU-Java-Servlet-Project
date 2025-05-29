package model;

public class TicketProduct {
	
	private Long ticketProductId;
	private Long ticketId;
	private Long productId;
	private Long price;
	private Integer discountPercentage;
	private Integer quantity;
	private Long totalPrice;
	
	public TicketProduct() {}
	
	public TicketProduct(Long ticketProductId, Long ticketId, Long productId, Long price, Integer discountPercentage,
			Integer quantity, Long totalPrice) {
		this.ticketProductId = ticketProductId;
		this.ticketId = ticketId;
		this.productId = productId;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public Long getTicketProductId() {
		return this.ticketProductId;
	}

	public void setTicketProductId(Long ticketProductId) {
		this.ticketProductId = ticketProductId;
	}

	public Long getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

}
