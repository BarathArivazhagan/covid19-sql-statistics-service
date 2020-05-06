package org.fiftyhands.statistics.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidMortalityDTO {
	
	private Long death_id;
	
	private Long province_death_id;
	
	private String case_id;
	
	private String age;
	
	private String sex;
	
	private String health_region;
	
	private String province;
	
	private String country;
	
	private String date_death_report;
	
	private String death_source;
	
	private String additional_info;
	
	private String additional_source;

	public Long getDeath_id() {
		return death_id;
	}

	public void setDeath_id(Long death_id) {
		this.death_id = death_id;
	}

	public Long getProvince_death_id() {
		return province_death_id;
	}

	public void setProvince_death_id(Long province_death_id) {
		this.province_death_id = province_death_id;
	}

	public String getCase_id() {
		return case_id;
	}

	public void setCase_id(String case_id) {
		this.case_id = case_id;
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

	public String getHealth_region() {
		return health_region;
	}

	public void setHealth_region(String health_region) {
		this.health_region = health_region;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDate_death_report() {
		return date_death_report;
	}

	public void setDate_death_report(String date_death_report) {
		this.date_death_report = date_death_report;
	}

	public String getDeath_source() {
		return death_source;
	}

	public void setDeath_source(String death_source) {
		this.death_source = death_source;
	}

	public String getAdditional_info() {
		return additional_info;
	}

	public void setAdditional_info(String additional_info) {
		this.additional_info = additional_info;
	}

	public String getAdditional_source() {
		return additional_source;
	}

	public void setAdditional_source(String additional_source) {
		this.additional_source = additional_source;
	}
	
	

}
