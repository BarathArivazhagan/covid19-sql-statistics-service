package org.fiftyhands.statistics.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonFormat;


public class QuarantineStatsByProvince {
	
		private String			patientName;
		
		@JsonFormat(pattern = "MM/dd/yyyy")
		private LocalDateTime	quarantineDate;
		
		private String			firstName;
		private String			lastName;
		private String			location;
		private String			gender;
		private String			bloodGroup;
		private String			pregnant;
		private Integer			age;
		@JsonFormat(pattern = "MM/dd/yyyy")
		private LocalDate		birthDate;

		private String			symptom;
		private String			travel;
		private String			travelLocation;

		@JsonFormat(pattern = "MM/dd/yyyy")
		private LocalDate		returnDate;
		private String			travelInfo;
		private String			layover;
		private String			familyTravel;
		private String			attendedgathering;
		private List<String>	associatedPeople;
		private String			office;

		@JsonFormat(pattern = "MM/dd/yyyy")
		private LocalDate		lastOfficeDate;
		private String			closeContact;
		private String			selfQuarantine;

		@JsonFormat(pattern = "MM/dd/yyyy")
		private LocalDate		selfQuarantineDate;
		private String			selfQuarantineLocation;

}
