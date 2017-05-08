package de.th_koeln.doctorcalendar.gui.user.findmedicalappointment;

import java.util.List;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class FindMedicalAppointmentModel {

	private FindMedicalAppointmentSearchParameter searchParameter;
	private List<MedicalAppointment> medicalAppointments;
	private MedicalAppointment selectedMedicalAppointment;

	public FindMedicalAppointmentModel(FindMedicalAppointmentSearchParameter aSearchParameter, List<MedicalAppointment> aMedicalAppointments) {
		super();
		searchParameter = aSearchParameter;
		medicalAppointments = aMedicalAppointments;
	}

	public FindMedicalAppointmentSearchParameter getSearchParameter() {
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

}
