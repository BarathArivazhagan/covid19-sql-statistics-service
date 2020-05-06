package org.fiftyhands.statistics.app.utils;

public class ProvinceUtils {
	
	public static String resolveProvinceName(String provinceName) {
		String name = "";
		
		switch(provinceName.trim()) {
			case "Alberta" : name = "Alberta";break;
			case "BC" : name = "British Columbia";break;
			case "Manitoba" : name = "Manitoba";break;
			case "New Brunswick" : name = "New Brunswick";break;
			case "NL" : name = "Newfoundland and Labrador";break;
			case "Nova Scotia" : name = "Nova Scotia";break;
			case "Ontario" : name = "Ontario";break;
			case "PEI" : name = "Prince Edward Island";break;
			case "Quebec" : name = "Quebec";break;
			case "Saskatchewan" : name = "Saskatchewan";break;
			case "NWT" : name = "Northwest Territories";break;
			case "Nunavut" : name = "Nunavut";break;
			case "Yukon" : name = "Yukon";break;
			default: name= "Canada";break;
		}
		
		return name;
	}

}
