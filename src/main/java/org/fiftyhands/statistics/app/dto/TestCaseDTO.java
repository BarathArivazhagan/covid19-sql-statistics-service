package org.fiftyhands.statistics.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCaseDTO {
	
	private String date_testing;
	
	private String province;
	
	private String cumulative_testing;
	
	private String testing_info;
	
	private String countryName;

	public String getDate_testing() {
		return date_testing;
	}

	public void setDate_testing(String date_testing) {
		this.date_testing = date_testing;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCumulative_testing() {
		return cumulative_testing;
	}

	public void setCumulative_testing(String cumulative_testing) {
		this.cumulative_testing = cumulative_testing;
	}

	public String getTesting_info() {
		return testing_info;
	}

	public void setTesting_info(String testing_info) {
		this.testing_info = testing_info;
	}
	
	

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public TestCaseDTO() {
		super();

	}
	
	
	

}
