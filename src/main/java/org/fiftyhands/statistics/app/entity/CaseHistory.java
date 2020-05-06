package org.fiftyhands.statistics.app.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;



@Entity(name = "case_history_by_province")
public class CaseHistory implements Serializable {
	

	private static final long serialVersionUID = -5621290389097430113L;

	@Id
	@GeneratedValue(generator = "CASE_HISTORY_BY_PROVINCE_GENERATOR", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "CASE_HISTORY_BY_PROVINCE_GENERATOR", sequenceName = "CASE_HISTORY_BY_PROVINCE_SEQUENCE",allocationSize=1)
	private Long id;
	
	private Long caseId;
	
	private Long provincialCaseId;
	
	private String ageRange;
	
	private String gender;
	
	private String healthRegion;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="province_id",nullable = false)
	private Province province;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="country_id",nullable = false)
	private Country country;
	
	private String dateReport;
	
	private String reportWeek;
	
	private String travelYn;
	
	private String travelHistoryCountry;
	
	private String locallyAcquired;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCaseId() {
		return caseId;
	}

	public void setCaseId(Long caseId) {
		this.caseId = caseId;
	}

	public Long getProvincialCaseId() {
		return provincialCaseId;
	}

	public void setProvincialCaseId(Long provincialCaseId) {
		this.provincialCaseId = provincialCaseId;
	}

	public String getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(String ageRange) {
		this.ageRange = ageRange;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getHealthRegion() {
		return healthRegion;
	}

	public void setHealthRegion(String healthRegion) {
		this.healthRegion = healthRegion;
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

	public String getDateReport() {
		return dateReport;
	}

	public void setDateReport(String dateReport) {
		this.dateReport = dateReport;
	}

	public String getReportWeek() {
		return reportWeek;
	}

	public void setReportWeek(String reportWeek) {
		this.reportWeek = reportWeek;
	}

	public String getTravelYn() {
		return travelYn;
	}

	public void setTravelYn(String travelYn) {
		this.travelYn = travelYn;
	}

	public String getTravelHistoryCountry() {
		return travelHistoryCountry;
	}

	public void setTravelHistoryCountry(String travelHistoryCountry) {
		this.travelHistoryCountry = travelHistoryCountry;
	}

	public String getLocallyAcquired() {
		return locallyAcquired;
	}

	public void setLocallyAcquired(String locallyAcquired) {
		this.locallyAcquired = locallyAcquired;
	}

	@Override
	public String toString() {
		return "CaseHistory [id=" + id + ", caseId=" + caseId + ", provincialCaseId=" + provincialCaseId + ", ageRange="
				+ ageRange + ", gender=" + gender + ", healthRegion=" + healthRegion + ", province=" + province
				+ ", country=" + country + ", dateReport=" + dateReport + ", reportWeek=" + reportWeek + ", travelYn="
				+ travelYn + ", travelHistoryCountry=" + travelHistoryCountry + ", locallyAcquired=" + locallyAcquired
				+ "]";
	}

	public CaseHistory() {
		super();
	}
	
	
	
	
	

}


