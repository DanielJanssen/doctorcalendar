package de.th_koeln.doctorcalendar.gui.doctor.find;

import java.util.List;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class FindDoctorModel {

	private FindDoctorSearchParameter searchParameter;
	private List<MedicalAppointment> medicalAppointments;
	private MedicalAppointment selectedMedicalAppointment;

	public FindDoctorModel(FindDoctorSearchParameter aSearchParameter, List<MedicalAppointment> aMedicalAppointments) {
		super();
		searchParameter = aSearchParameter;
		medicalAppointments = aMedicalAppointments;
	}

	public FindDoctorSearchParameter getSearchParameter() {
		return searchParameter;
	}

	public List<MedicalAppointment> getMedicalAppointments() {
		return medicalAppointments;
	}

	public MedicalAppointment getSelectedMedicalAppointment() {
		return selectedMedicalAppointment;
	}

	public void setSelectedMedicalAppointment(MedicalAppointment aSelectedMedicalAppointment) {
		selectedMedicalAppointment = aSelectedMedicalAppointment;
	}

	public void setSearchParameter(FindDoctorSearchParameter aSearchParameter) {
		searchParameter = aSearchParameter;
	}

	public void setMedicalAppointments(List<MedicalAppointment> aMedicalAppointments) {
		medicalAppointments = aMedicalAppointments;
	}

}
