package org.fiftyhands.statistics.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "covid_tests_stats_by_province")
public class CovidTestsByProvince extends CovidTestsCommonEntity {

	private static final long serialVersionUID = -666222586806959008L;

	@Id
	@GeneratedValue
	private Integer id;
	
	private String lastUpdatedTime;

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CovidTestsByProvince() {
		super();
	}
	
	
	

}
