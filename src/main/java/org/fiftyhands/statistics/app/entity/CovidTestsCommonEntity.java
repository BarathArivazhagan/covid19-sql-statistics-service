package org.fiftyhands.statistics.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class CovidTestsCommonEntity implements Serializable{
	
	private static final long serialVersionUID = 2872887856042848893L;

	@Column(name="total_tests")
	private Long totalTests;
	
	@Column(name="positive_tests")
	private Long positiveTests;
	
	@Column(name="negative_tests")
	private Long negativeTests;
	
	@Column(name="today_tests")
	private Long todayTests;

	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="province_id",nullable = false)
	private Province province;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id",nullable = false)
	private Country country;

	public Long getTotalTests() {
		return totalTests;
	}

	public void setTotalTests(Long totalTests) {
		this.totalTests = totalTests;
	}

	public Long getPositiveTests() {
		return positiveTests;
	}

	public void setPositiveTests(Long positiveTests) {
		this.positiveTests = positiveTests;
	}

	public Long getNegativeTests() {
		return negativeTests;
	}

	public void setNegativeTests(Long negativeTests) {
		this.negativeTests = negativeTests;
	}
	
	public Long getTodayTests() {
		return todayTests;
	}

	public void setTodayTests(Long todayTests) {
		this.todayTests = todayTests;
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

	public CovidTestsCommonEntity() {
		super();
	}
	
	


}
