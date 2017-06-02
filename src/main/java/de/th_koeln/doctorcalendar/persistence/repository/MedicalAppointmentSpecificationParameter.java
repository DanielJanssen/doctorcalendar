package de.th_koeln.doctorcalendar.persistence.repository;

import java.sql.Time;
import java.util.Date;

import de.th_koeln.doctorcalendar.application.entity.MedicalOffice;
import de.th_koeln.doctorcalendar.application.entity.enums.MedicalAppointmentState;
import de.th_koeln.doctorcalendar.application.entity.enums.Speciality;
import de.th_koeln.doctorcalendar.gui.doctor.find.FindDoctorSearchParameter;
import de.th_koeln.doctorcalendar.gui.user.medicalappointment.find.FindMedicalAppointmentSearchParameter;

public class MedicalAppointmentSpecificationParameter {

	private String medicalOfficeName;
	private Date medicalAppointmentDateFrom;
	private Date medicalAppointmentDateTo;
	private Time medicalAppointmentTimeFrom;
	private Time medicalAppointmentTimeTo;
	private Speciality speciality;
	private Integer maximumDistanceInKm;
	private MedicalAppointmentState state;
	private String patientName;

	public MedicalAppointmentSpecificationParameter(FindMedicalAppointmentSearchParameter aSearchParameter) {
		super();
		medicalOfficeName = aSearchParameter.getMedicalOfficeName();
		medicalAppointmentDateFrom = aSearchParameter.getMedicalAppointmentDateFrom();
		medicalAppointmentDateTo = aSearchParameter.getMedicalAppointmentDateTo();
		medicalAppointmentTimeFrom = aSearchParameter.getMedicalAppointmentTimeFrom();
		medicalAppointmentTimeTo = aSearchParameter.getMedicalAppointmentTimeTo();
		speciality = aSearchParameter.getSpeciality();
		maximumDistanceInKm = aSearchParameter.getMaximumDistanceInKm();
		getOnlyFreeFutureMedicalAppointments();
	}

	private void getOnlyFreeFutureMedicalAppointments() {
		state = MedicalAppointmentState.FREE;
		if (medicalAppointmentDateFrom == null || medicalAppointmentDateFrom.before(new Date())) {
			medicalAppointmentDateFrom = new Date();
		}
	}

	public MedicalAppointmentSpecificationParameter(FindDoctorSearchParameter aSearchParameter, MedicalOffice aMedicalOffe) {
		super();
		medicalOfficeName = aMedicalOffe.getName();
		medicalAppointmentDateFrom = aSearchParameter.getMedicalAppointmentDateFrom();
		medicalAppointmentDateTo = aSearchParameter.getMedicalAppointmentDateTo();
		medicalAppointmentTimeFrom = aSearchParameter.getMedicalAppointmentTimeFrom();
		medicalAppointmentTimeTo = aSearchParameter.getMedicalAppointmentTimeTo();
		state = aSearchParameter.getState();
		patientName = aSearchParameter.getPatientName();
	}

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

	public MedicalAppointmentState getState() {
		return state;
	}

	public void setState(MedicalAppointmentState aState) {
		state = aState;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String aPatientName) {
		patientName = aPatientName;
	}

}
