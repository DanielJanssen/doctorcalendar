package de.th_koeln.doctorcalendar.gui.user.findmedicalappointment;

import java.util.List;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class FindMedicalAppointmentModel {

	private FindMedicalAppointmentSearchParameter searchParameter;
	private List<MedicalAppointment> medicalAppointments;
	private MedicalAppointment selectedMedicalAppointment;

	public FindMedicalAppointmentModel(FindMedicalAppointmentSearchParameter aSearchParameter, List<MedicalAppointment> aMedicalAppointments,
			MedicalAppointment aSelectedMedicalAppointment) {
		super();
		searchParameter = aSearchParameter;
		medicalAppointments = aMedicalAppointments;
		selectedMedicalAppointment = aSelectedMedicalAppointment;
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

}
