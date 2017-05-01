package de.th_koeln.doctorcalendar.application.entity.uuid;

import java.util.UUID;

public class UuidGenerator {

	public static String buildUuidString() {
		return UUID.randomUUID().toString();
	}
}
