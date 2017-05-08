package de.th_koeln.doctorcalendar.gui.user.findmedicalappointment;

import java.util.Date;

import de.th_koeln.doctorcalendar.application.entity.enums.Speciality;

public class FindMedicalAppointmentSearchParameter {

	private String medicalOfficeName;
	private Date medicalAppointmentDateFrom;
	private Date medicalAppointmentDateTo;
	private Date medicalAppointmentTimeFrom;
	private Date medicalAppointmentTimeTo;
	private Speciality speciality;
	private Integer maximumDistanceInKm;

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

	public Date getMedicalAppointmentTimeFrom() {
		return medicalAppointmentTimeFrom;
	}

	public void setMedicalAppointmentTimeFrom(Date aMedicalAppointmentTimeFrom) {
		medicalAppointmentTimeFrom = aMedicalAppointmentTimeFrom;
	}

	public Date getMedicalAppointmentTimeTo() {
		return medicalAppointmentTimeTo;
	}

	public void setMedicalAppointmentTimeTo(Date aMedicalAppointmentTimeTo) {
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

}
