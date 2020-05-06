package org.fiftyhands.statistics.app.scheduler;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.dto.CovidStatsFromSource;
import org.fiftyhands.statistics.app.service.CovidCasesStatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

//@Component
public class HealthInfoBaseSourceSchedulerTask {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final CsvMapper mapper = new CsvMapper();
	private final CsvSchema schema = CsvSchema.emptySchema().withHeader();
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private CovidCasesStatsService covidCasesStatsService;
	
	@Value("${health.infobase.datasource.url}")
	private String datasourceURL;
	
	@Value("${java.io.tmpdir}")
	private String tempDir;
	
	
	//@Scheduled(cron = "0 */2 * * * *")
	@PostConstruct
	@Async
	public void pullSource() throws IOException {
		logger.info("The health info base datasource sheduled now at  {}", new Date());
		HttpHeaders headers = new HttpHeaders();
		headers.add("content-type", "text/csv");
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<byte[]> responseEntity =restTemplate.exchange(datasourceURL,HttpMethod.GET,requestEntity, byte[].class);
		if(responseEntity.getStatusCode().is2xxSuccessful()) {
			logger.info("Success in retriving the data from health datasource {}",responseEntity.getStatusCode().value());
			String fileName=tempDir.concat("covid_"+LocalDate.now()+UUID.randomUUID()+".csv");
			logger.info("File name formed {}",fileName);
			Files.write(Paths.get(fileName), responseEntity.getBody());
			File csvFile = new File(fileName);
			List<CovidStatsFromSource> stats = new ArrayList<>();
			MappingIterator<CovidStatsFromSource> it = mapper.readerFor(CovidStatsFromSource.class)
					   .with(schema)
					   .readValues(csvFile);
			while (it.hasNext()) {
				CovidStatsFromSource row = it.next();
				if(logger.isDebugEnabled()) {
					logger.info(Objects.toString(row));
				}
				row.setCountryName("Canada"); // This source is only for canada so setting country to canada
				stats.add(row);
				
			}
			this.setupCovidRawData(stats);
			if(csvFile !=null ) csvFile.delete(); // clean up the file. TODO: may be upload to blob storage for future retrievals
		}else {
			logger.error("Error in retriving the data from health datasource {}",responseEntity.getStatusCode().value());
			logger.error("Error trace {}",responseEntity.getBody());
		}
	}
	
	
	protected void setupCovidRawData(List<CovidStatsFromSource> sources) {
		this.covidCasesStatsService.saveCovidStatsFromSource(sources);
	}
	
	
	
	
	
	protected CsvSchema buildSchema() {
		return CsvSchema.builder()
				  .addColumn("pruid", CsvSchema.ColumnType.NUMBER)
				  .addColumn("prname", CsvSchema.ColumnType.STRING)
				  .addColumn("prnameFR", CsvSchema.ColumnType.STRING)
				  .addColumn("date", CsvSchema.ColumnType.STRING)
				  .addColumn("numconf", CsvSchema.ColumnType.NUMBER)
				  .addColumn("numprob", CsvSchema.ColumnType.NUMBER)
				  .addColumn("numdeaths", CsvSchema.ColumnType.NUMBER)
				  .addColumn("numtotal", CsvSchema.ColumnType.NUMBER)
				  .addColumn("numtested", CsvSchema.ColumnType.NUMBER)
				  .addColumn("numrecover", CsvSchema.ColumnType.STRING)
				  .addColumn("percentrecover", CsvSchema.ColumnType.NUMBER_OR_STRING)
				  .addColumn("ratetested", CsvSchema.ColumnType.NUMBER_OR_STRING)
				  .addColumn("numtoday", CsvSchema.ColumnType.NUMBER)
				  .addColumn("percentoday", CsvSchema.ColumnType.NUMBER_OR_STRING)	  
				  .build();
	}

}
