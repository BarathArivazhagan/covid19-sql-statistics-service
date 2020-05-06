package org.fiftyhands.statistics.app.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
public class CovidCasesCommonEntity {
	
	@Column(name="confirm_cases")
	private Long confirmCases;
	
	@Column(name="death_cases")
	private Long deathCases;
	
	@Column(name="probable_cases")
	private Long probableCases;

	@Column(name="recovered_cases")
	private Long recoveredCases;

	@Column(name="total_cases")
	private Long totalCases;
	
	@Column(name="today_cases")
	private Long todayCases;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="province_id",nullable = false)
	private Province province;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id",nullable = false)
	private Country country;


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

	public Long getTodayCases() {
		return todayCases;
	}

	public void setTodayCases(Long todayCases) {
		this.todayCases = todayCases;
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

	@Override
	public String toString() {
		return "CovidCasesCommonEntity{" +
				"confirmCases=" + confirmCases +
				", deathCases=" + deathCases +
				", probableCases=" + probableCases +
				", recoveredCases=" + recoveredCases +
				", totalCases=" + totalCases +
				", todayCases=" + todayCases +
				", province=" + province +
				", country=" + country +
				'}';
	}
}
