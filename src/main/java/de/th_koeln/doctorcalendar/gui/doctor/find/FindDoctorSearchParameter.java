package de.th_koeln.doctorcalendar.gui.doctor.find;

import java.sql.Time;
import java.util.Date;

import de.th_koeln.doctorcalendar.application.entity.enums.MedicalAppointmentState;

public class FindDoctorSearchParameter {

	private String patientName;
	private Date medicalAppointmentDateFrom;
	private Date medicalAppointmentDateTo;
	private Time medicalAppointmentTimeFrom;
	private Time medicalAppointmentTimeTo;
	private MedicalAppointmentState state;

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String aPatientName) {
		patientName = aPatientName;
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

	public MedicalAppointmentState getState() {
		return state;
	}

	public void setState(MedicalAppointmentState aState) {
		state = aState;
	}
}