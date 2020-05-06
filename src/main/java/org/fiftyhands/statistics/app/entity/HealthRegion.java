package org.fiftyhands.statistics.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="health_regions")
public class HealthRegion extends GeoLocation {
	
	@Id
	@GeneratedValue(generator = "HEALTHREGION_BY_PROVINCE_GENERATOR", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "HEALTHREGION_BY_PROVINCE_GENERATOR", sequenceName = "HEALTHREGION_BY_PROVINCE_SEQUENCE",allocationSize=1)
	private Long id;
	
	@Column(name="health_region")
	private String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="province_id",nullable = false)
	private Province province;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id",nullable = false)
	private Country country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public HealthRegion() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	


}
