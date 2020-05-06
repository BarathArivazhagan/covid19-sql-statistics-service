package org.fiftyhands.statistics.app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "provinces")
public class Province extends GeoLocation {
	
	private static final long serialVersionUID = 7881775696352491669L;

	@Id
	@Column(name="province_id")
	private Integer provinceId;
	
	@Column(name="province_name")
	private String provinceName;
	
	@Column(name="province_fr")
	private String provinceFR;
	
	@Column(name="province_population")
	private Long population;
	
	@Column(name="province_short_form")
	private String provinceShortForm;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id",nullable = false)
	private Country country;
	

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Long getPopulation() {
		return population;
	}

	public void setPopulation(Long population) {
		this.population = population;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getProvinceFR() {
		return provinceFR;
	}

	public void setProvinceFR(String provinceFR) {
		this.provinceFR = provinceFR;
	}
	
	public String getProvinceShortForm() {
		return provinceShortForm;
	}

	public void setProvinceShortForm(String provinceShortForm) {
		this.provinceShortForm = provinceShortForm;
	}

	public Province() {
		super();
	}

	@Override
	public String toString() {
		return "Province [provinceId=" + provinceId + ", provinceName=" + provinceName + ", provinceFR=" + provinceFR
				+ ", population=" + population + ", provinceShortForm=" + provinceShortForm + ", country=" + country
				+ "]";
	}

	
	
	

	
	
	
	
	

}
