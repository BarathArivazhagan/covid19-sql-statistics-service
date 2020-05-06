package org.fiftyhands.statistics.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "covid_mortality_raw_data")
public class CovidMortalityRawData implements Serializable{

	private static final long serialVersionUID = -4405058283511658650L;

	@Id
	@Column(name = "death_id")
    private Long id;
	
	@Column(name = "province_death_id")
	private Long provinceDeathId;
	
	@Column(name = "case_id")
	private String caseId;
	
	@Column(name = "age")
	private String age;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "health_region")
	private String healthRegion;
	
	@Column(name = "date_death_report")
	private String dateDeathReport;
	
	@Column(name = "death_source")
	private String deathSource;
	
	@Column(name = "additional_info")
	private String additionalInfo;
	
	@Column(name = "additional_source")
	private String additionalSource;
	
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

	public Long getProvinceDeathId() {
		return provinceDeathId;
	}

	public void setProvinceDeathId(Long provinceDeathId) {
		this.provinceDeathId = provinceDeathId;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getHealthRegion() {
		return healthRegion;
	}

	public void setHealthRegion(String healthRegion) {
		this.healthRegion = healthRegion;
	}

	public String getDateDeathReport() {
		return dateDeathReport;
	}

	public void setDateDeathReport(String dateDeathReport) {
		this.dateDeathReport = dateDeathReport;
	}

	public String getDeathSource() {
		return deathSource;
	}

	public void setDeathSource(String deathSource) {
		this.deathSource = deathSource;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public String getAdditionalSource() {
		return additionalSource;
	}

	public void setAdditionalSource(String additionalSource) {
		this.additionalSource = additionalSource;
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

	public CovidMortalityRawData() {
		super();
	}
	
	
	
	
	

	


}
