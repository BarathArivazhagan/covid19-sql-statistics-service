package org.fiftyhands.statistics.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidStatsFromSource {
	
	private Long pruid;
	
	private String prname;
	
	private String prnameFR;
	
	private String date;
	
	private Long numconf;
	
	private Long numprob;
	
	private Long numdeaths;
	
	private Long numtotal;
	
	private Long numtested;
	
	private String numrecover;
	
	private String percentrecover;
	
	private Long ratetested;
	
	private Long numtoday;
	
	private String percentoday;
	
	private String countryName;

	public Long getPruid() {
		return pruid;
	}

	public void setPruid(Long pruid) {
		this.pruid = pruid;
	}

	public String getPrname() {
		return prname;
	}

	public void setPrname(String prname) {
		this.prname = prname;
	}

	public String getPrnameFR() {
		return prnameFR;
	}

	public void setPrnameFR(String prnameFR) {
		this.prnameFR = prnameFR;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getNumconf() {
		return numconf;
	}

	public void setNumconf(Long numconf) {
		this.numconf = numconf;
	}

	public Long getNumprob() {
		return numprob;
	}

	public void setNumprob(Long numprob) {
		this.numprob = numprob;
	}

	public Long getNumdeaths() {
		return numdeaths;
	}

	public void setNumdeaths(Long numdeaths) {
		this.numdeaths = numdeaths;
	}

	public Long getNumtotal() {
		return numtotal;
	}

	public void setNumtotal(Long numtotal) {
		this.numtotal = numtotal;
	}

	public Long getNumtested() {
		return numtested;
	}

	public void setNumtested(Long numtested) {
		this.numtested = numtested;
	}

	public String getNumrecover() {
		return numrecover;
	}

	public void setNumrecover(String numrecover) {
		this.numrecover = numrecover;
	}

	public String getPercentrecover() {
		return percentrecover;
	}

	public void setPercentrecover(String percentrecover) {
		this.percentrecover = percentrecover;
	}

	public Long getRatetested() {
		return ratetested;
	}

	public void setRatetested(Long ratetested) {
		this.ratetested = ratetested;
	}

	public Long getNumtoday() {
		return numtoday;
	}

	public void setNumtoday(Long numtoday) {
		this.numtoday = numtoday;
	}

	public String getPercentoday() {
		return percentoday;
	}

	public void setPercentoday(String percentoday) {
		this.percentoday = percentoday;
	}
	
	


	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "CovidStatsFromSource{" +
				"pruid=" + pruid +
				", prname='" + prname + '\'' +
				", prnameFR='" + prnameFR + '\'' +
				", date='" + date + '\'' +
				", numconf=" + numconf +
				", numprob=" + numprob +
				", numdeaths=" + numdeaths +
				", numtotal=" + numtotal +
				", numtested=" + numtested +
				", numrecover=" + numrecover +
				", percentrecover=" + percentrecover +
				", ratetested=" + ratetested +
				", numtoday=" + numtoday +
				", percentoday=" + percentoday +
				'}';
	}
}
