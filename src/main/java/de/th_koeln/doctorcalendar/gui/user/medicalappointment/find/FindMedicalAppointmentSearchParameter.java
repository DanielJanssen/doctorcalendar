package de.th_koeln.doctorcalendar.gui.user.medicalappointment.find;

import java.sql.Time;
import java.util.Date;

import de.th_koeln.doctorcalendar.application.entity.enums.Speciality;

public class FindMedicalAppointmentSearchParameter {

	private String medicalOfficeName;
	private Date medicalAppointmentDateFrom;
	private Date medicalAppointmentDateTo;
	private Time medicalAppointmentTimeFrom;
	private Time medicalAppointmentTimeTo;
	private Speciality speciality;
	private Integer maximumDistanceInKm;
	private String userName;

	public String getMedicalOfficeName() {
		return medicalOfficeName;
	}

	public void setMedicalOfficeName(String aMedicalOfficeName) {
		medicalOfficeName = aMedicalOfficeName;
	}

	public Date getMedicalAppointmentDateFrom() {
		return medicalAppointmentDateFrom;
	}

	public void setMedicalAppointmentDateFrom(Date aMedicalAppointmentDateFrom) {
		medicalAppointmentDateFrom = aMedicalAppointmentDateFrom;
	}

	public Date getMedicalAppointmentDateTo() {
		return medicalAppointmentDateTo;
	}

	public void setMedicalAppointmentDateTo(Date aMedicalAppointmentDateTo) {
		medicalAppointmentDateTo = aMedicalAppointmentDateTo;
	}

	public Time getMedicalAppointmentTimeFrom() {
		return medicalAppointmentTimeFrom;
	}

	public void setMedicalAppointmentTimeFrom(Time aMedicalAppointmentTimeFrom) {
		medicalAppointmentTimeFrom = aMedicalAppointmentTimeFrom;
	}

	public Time getMedicalAppointmentTimeTo() {
		return medicalAppointmentTimeTo;
	}

	public void setMedicalAppointmentTimeTo(Time aMedicalAppointmentTimeTo) {
		medicalAppointmentTimeTo = aMedicalAppointmentTimeTo;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality aSpeciality) {
		speciality = aSpeciality;
	}

	public Integer getMaximumDistanceInKm() {
		return maximumDistanceInKm;
	}

	public void setMaximumDistanceInKm(Integer aMaximumDistanceInKm) {
		maximumDistanceInKm = aMaximumDistanceInKm;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String aUserName) {
		userName = aUserName;
	}

}
