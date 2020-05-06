package org.fiftyhands.statistics.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "covid_tests_raw_data")
public class CovidTestsRawData extends CovidTestsCommonEntity{

	private static final long serialVersionUID = 8525275331545317731L;

	@Id
	@GeneratedValue(generator = "TESTS_RAW_DATA_BY_PROVINCE_GENERATOR", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "TESTS_RAW_DATA_BY_PROVINCE_GENERATOR", sequenceName = "TESTS_RAW_DATA_BY_PROVINCE_SEQUENCE",allocationSize=1)
	private Long id;
	
	@Column(name = "date_of_testing")
	private String dateOfTesting;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDateOfTesting() {
		return dateOfTesting;
	}

	public void setDateOfTesting(String dateOfTesting) {
		this.dateOfTesting = dateOfTesting;
	}

	public CovidTestsRawData() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "CovidTestsRawData [id=" + id + ", dateOfTesting=" + dateOfTesting + "]";
	}
	
	
	
	


}
