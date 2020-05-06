package org.fiftyhands.statistics.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CaseHistorySource {
	
	private Long case_id;
	
	private Long provincial_case_id;
	
	private String age;
	
	private String sex;
	
	private String health_region;

	private String province;
	
	private String country;
	
	private String date_report;
	
	private String report_week;
	
	private String travel_yn;
	
	private String travel_history_country;
	
	private String locally_acquired;

	public Long getCase_id() {
		return case_id;
	}

	public void setCase_id(Long case_id) {
		this.case_id = case_id;
	}

	public Long getProvincial_case_id() {
		return provincial_case_id;
	}

	public void setProvincial_case_id(Long provincial_case_id) {
		this.provincial_case_id = provincial_case_id;
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

	public String getDate_report() {
		return date_report;
	}

	public void setDate_report(String date_report) {
		this.date_report = date_report;
	}

	public String getReport_week() {
		return report_week;
	}

	public void setReport_week(String report_week) {
		this.report_week = report_week;
	}

	public String getTravel_yn() {
		return travel_yn;
	}

	public void setTravel_yn(String travel_yn) {
		this.travel_yn = travel_yn;
	}

	public String getTravel_history_country() {
		return travel_history_country;
	}

	public void setTravel_history_country(String travel_history_country) {
		this.travel_history_country = travel_history_country;
	}

	public String getLocally_acquired() {
		return locally_acquired;
	}

	public void setLocally_acquired(String locally_acquired) {
		this.locally_acquired = locally_acquired;
	}
	
	
	

}
