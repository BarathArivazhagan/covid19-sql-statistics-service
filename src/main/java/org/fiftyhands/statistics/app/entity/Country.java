package org.fiftyhands.statistics.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "countries")
public class Country extends GeoLocation {
	
	private static final long serialVersionUID = 3781891759498001711L;

	@Id
	private Integer countryId;
	
	@Column(name = "country_name")
	private String countryName;
	
	@Column(name = "country_population")
	private Long population;
	

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public Country() {
		super();
	}
	
	
	
	

}
