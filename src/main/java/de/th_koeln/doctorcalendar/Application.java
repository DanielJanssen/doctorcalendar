package de.th_koeln.doctorcalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.th_koeln.doctorcalendar.service.testdata.TestdataGenerator;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOGGER.info("Starting application");
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TestdataGenerator testdata) {
		return args -> {
			//			testdata.generateTestData();
		};
	}
}