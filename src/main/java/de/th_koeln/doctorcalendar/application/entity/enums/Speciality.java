package de.th_koeln.doctorcalendar.application.entity.enums;

public enum Speciality {

	//@formatter:off
	INTERNIST("Allgemeinmedizin"),
	DENTIST("Zahnarzt");
	//@formatter:on
	private String germanDescription;

	private Speciality(String aGermanDescription) {
		germanDescription = aGermanDescription;
	}

	public String getGermanDescription() {
		return germanDescription;
	}
}