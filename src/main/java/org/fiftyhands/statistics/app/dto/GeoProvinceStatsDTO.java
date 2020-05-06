package org.fiftyhands.statistics.app.dto;

public class GeoProvinceStatsDTO {
	
	private Integer pruid;
	
	private String prname;
	
	private String prnameFR;
	
	private Double latitude;
	
	private Double longitude;
	
	private Long population;

	public Integer getPruid() {
		return pruid;
	}

	public void setPruid(Integer pruid) {
		this.pruid = pruid;
	}

	public String getPrname() {
		return prname;
	}

	public void setPrname(String prname) {
		this.prname = prname;
	}
	

	public String getPrnameFR() {
		return prnameFR;
	}

	public void setPrnameFR(String prnameFR) {
		this.prnameFR = prnameFR;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public GeoProvinceStatsDTO() {
		super();
	}
	
	
	

}
