package model;


public class Theater {
	
	private Long theaterId;
	private String name;
	private String location;
	
	public Theater() {}

	public Theater(Long theaterId, String name, String location) {
		this.theaterId = theaterId;
		this.name = name;
		this.location = location;
	}
	
	public Long getTheaterId() {
		return this.theaterId;
	}
	
	public void setTheaterId(Long theaterId) {
		this.theaterId = theaterId;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}

}
