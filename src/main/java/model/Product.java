package model;

public class Product {
	
	private Long productId;
	private String name;
	private Long price;
	private Integer discountPercentage;
	private Integer stock;
	
	public Product() {}

	public Product(Long productId, String name, Long price, Integer discountPercentage, Integer stock) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.discountPercentage = discountPercentage;
		this.stock = stock;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Integer getStock() {
		return this.stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

}
