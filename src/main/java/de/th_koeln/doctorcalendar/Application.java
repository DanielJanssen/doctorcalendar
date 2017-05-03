package de.th_koeln.doctorcalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		LOGGER.info("Starting application");
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {

		};
	}
}

// TODO rt57, 03.05.2017: MedicalAppointment fehlt Caregiving
// TODO rt57, 03.05.2017: Address fehlt Hausnummer
// TODO rt57, 03.05.2017: Status fehlt für Medical Appointment (reserviert, bestätigt)
