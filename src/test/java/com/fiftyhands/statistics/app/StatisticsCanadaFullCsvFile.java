package com.fiftyhands.statistics.app;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class StatisticsCanadaFullCsvFile {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void test() throws IOException {
		String url="https://www150.statcan.gc.ca/t1/wds/rest/getFullTableDownloadCSV/14100287/en";
			
			HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "text/csv");
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<byte[]> responseEntity =restTemplate.exchange(url,HttpMethod.GET,requestEntity, byte[].class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			logger.info("Success in retriving the data from health datasource {}",responseEntity.getStatusCode().value());
			String fileName="covid_"+LocalDate.now()+UUID.randomUUID()+"statistics.csv";
			logger.info("File name formed {}",fileName);
			Files.write(Paths.get(fileName), responseEntity.getBody());
		}
	}

}
