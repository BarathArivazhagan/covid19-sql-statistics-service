package org.fiftyhands.statistics.app.entity;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class GeoLocation implements Serializable {
	
	private static final long serialVersionUID = 6994682640347425351L;

	private Double latitude;
	
	private Double longitude;
	
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




	public GeoLocation(Double latitude, Double longitude) {
		super();
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public GeoLocation() {
		super();
		
	}

	

}
