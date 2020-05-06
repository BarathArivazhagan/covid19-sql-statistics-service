package org.fiftyhands.statistics.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "cities")
public class City {
	
	
	@Id
	@Column(name= "city_id")
	private Integer cityId;
	
	@Column(name= "city_name")
	private String cityName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="province_id",nullable = false)
	private Province province;
	
	@Column(name= "city_population")
	private Long population;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public City() {
		super();
	}
	
	
	

}
