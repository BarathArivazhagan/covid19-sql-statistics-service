package org.fiftyhands.statistics.app.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthRegionDTO implements Serializable {
	
	private static final long serialVersionUID = -8645403568120250408L;

	private String province;
	
	private String health_region;
	
	private Double latitude;
	
	private Double longitude;

	

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getHealth_region() {
		return health_region;
	}

	public void setHealth_region(String health_region) {
		this.health_region = health_region;
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

	public HealthRegionDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
