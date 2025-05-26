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
		return ticketProductId;
	}

	public void setTicketProductId(Long ticketProductId) {
		this.ticketProductId = ticketProductId;
	}

	public Long getTicketId() {
		return ticketId;
	}

	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

}
