package org.fiftyhands.statistics.app.dto;

public class CovidCasesByCountryStats{
	
	
	private Long confirmCases;
	

	private Long deathCases;

	private Long probableCases;

	private Long recoveredCases;
	
	private Long totalCases;
	
	private Long todayCases;

	private String countryName;

	public Long getConfirmCases() {
		return confirmCases;
	}

	public void setConfirmCases(Long confirmCases) {
		this.confirmCases = confirmCases;
	}

	public Long getDeathCases() {
		return deathCases;
	}

	public void setDeathCases(Long deathCases) {
		this.deathCases = deathCases;
	}

	public Long getProbableCases() {
		return probableCases;
	}

	public void setProbableCases(Long probableCases) {
		this.probableCases = probableCases;
	}

	public Long getRecoveredCases() {
		return recoveredCases;
	}

	public void setRecoveredCases(Long recoveredCases) {
		this.recoveredCases = recoveredCases;
	}

	public Long getTotalCases() {
		return totalCases;
	}

	public void setTotalCases(Long totalCases) {
		this.totalCases = totalCases;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getTodayCases() {
		return todayCases;
	}

	public void setTodayCases(Long todayCases) {
		this.todayCases = todayCases;
	}
	
	


}
