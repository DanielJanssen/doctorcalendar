package de.th_koeln.doctorcalendar.gui.user.medicalappointment.past;

import java.util.List;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class PastMedicalAppointmentModel {

	private List<MedicalAppointment> medicalAppointments;
	private MedicalAppointment selectedMedicalAppointment;

	public PastMedicalAppointmentModel(List<MedicalAppointment> aMedicalAppointments) {
		super();
		medicalAppointments = aMedicalAppointments;
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
