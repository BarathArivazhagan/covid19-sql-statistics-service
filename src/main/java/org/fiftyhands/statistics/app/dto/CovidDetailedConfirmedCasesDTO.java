package org.fiftyhands.statistics.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CovidDetailedConfirmedCasesDTO {
	
	@JsonProperty("REF_DATE")
	private String referenceDate;
	
	@JsonProperty("GEO")
	private String geo;
	
	@JsonProperty("DGUID")
	private String dGuid;
	
	@JsonProperty("Case identifier number")
	private String caseIdentifyNumber;
	
	@JsonProperty("Case information")
	private String caseInformation;
	
	@JsonProperty("VALUE")
	private String value;

	public String getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}

	public String getGeo() {
		return geo;
	}

	public void setGeo(String geo) {
		this.geo = geo;
	}

	public String getdGuid() {
		return dGuid;
	}

	public void setdGuid(String dGuid) {
		this.dGuid = dGuid;
	}

	public String getCaseIdentifyNumber() {
		return caseIdentifyNumber;
	}

	public void setCaseIdentifyNumber(String caseIdentifyNumber) {
		this.caseIdentifyNumber = caseIdentifyNumber;
	}

	public String getCaseInformation() {
		return caseInformation;
	}

	public void setCaseInformation(String caseInformation) {
		this.caseInformation = caseInformation;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "CovidDetailedConfirmedCasesDTO [referenceDate=" + referenceDate + ", geo=" + geo + ", dGuid=" + dGuid
				+ ", caseIdentifyNumber=" + caseIdentifyNumber + ", caseInformation=" + caseInformation + ", value="
				+ value + "]";
	}
	
	
	


}
