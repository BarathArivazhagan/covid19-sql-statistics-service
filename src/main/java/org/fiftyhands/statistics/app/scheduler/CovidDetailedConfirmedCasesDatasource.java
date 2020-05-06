package org.fiftyhands.statistics.app.scheduler;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.dto.CovidDetailedConfirmedCasesDTO;
import org.fiftyhands.statistics.app.dto.CovidStatsFromSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

//@Component
public class CovidDetailedConfirmedCasesDatasource {
	
	@Autowired
	private ResourceLoader resourceLoader;
	private final CsvMapper mapper = new CsvMapper();
	private final CsvSchema schema = CsvSchema.emptySchema().withHeader();
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@PostConstruct
	public void pullDataSource() throws IOException {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	 File csvFile=	new ClassPathResource("13100766-27-04-2020.csv").getFile();
	 List<CovidStatsFromSource> stats = new ArrayList<>();
	MappingIterator<CovidDetailedConfirmedCasesDTO> it = mapper.readerFor(CovidDetailedConfirmedCasesDTO.class)
				   .with(schema)
				   .readValues(csvFile);
		while (it.hasNext()) {
			CovidDetailedConfirmedCasesDTO row = it.next();
			if(logger.isDebugEnabled()) {
				logger.info(Objects.toString(row));
			}
			//row.setCountryName("Canada"); // This source is only for canada so setting country to canada
			//stats.add(row);
			
		}	
	}

}
