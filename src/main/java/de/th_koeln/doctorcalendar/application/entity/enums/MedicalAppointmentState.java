package de.th_koeln.doctorcalendar.application.entity.enums;

public enum MedicalAppointmentState {

	//@formatter:off
	RESERVED("Reserviert"),
	ACCEPTED("Akzeptiert");
	//@formatter:on
	private String germanDescription;

	private MedicalAppointmentState(String aGermanDescription) {
		germanDescription = aGermanDescription;
	}

	public String getGermanDescription() {
		return germanDescription;
	}
}
