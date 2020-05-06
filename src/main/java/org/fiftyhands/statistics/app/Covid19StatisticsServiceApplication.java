package org.fiftyhands.statistics.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Covid19StatisticsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Covid19StatisticsServiceApplication.class, args);
	}

}
