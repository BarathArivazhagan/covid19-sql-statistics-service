package org.fiftyhands.statistics.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity(name = "covid_cases_raw_data")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidCasesRawData extends CovidCasesCommonEntity {
	
	@Id
	@GeneratedValue(generator = "CASE_RAW_DATA_BY_PROVINCE_GENERATOR", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "CASE_RAW_DATA_BY_PROVINCE_GENERATOR", sequenceName = "CASE_RAW_DATA_BY_PROVINCE_SEQUENCE",allocationSize=1)
	private Long id;
	
	@Column(name="created_date")
	private String created_date;
	
	@Column(name="date")
	private String date;

	public String getCreated_date() {
		return created_date;
	}

	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CovidCasesRawData() {
		super();

	}

	@Override
	public String toString() {
		return "CovidCasesRawData{" +
				"id=" + id +
				", created_date='" + created_date + '\'' +
				", date='" + date + '\'' +
				"} ";
	}

	
}
