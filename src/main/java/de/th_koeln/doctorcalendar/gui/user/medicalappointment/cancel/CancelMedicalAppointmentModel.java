package de.th_koeln.doctorcalendar.gui.user.medicalappointment.cancel;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class CancelMedicalAppointmentModel {

	private MedicalAppointment medicalAppointment;

	public CancelMedicalAppointmentModel(MedicalAppointment aMedicalAppointment) {
		super();
		medicalAppointment = aMedicalAppointment;
	}

	public MedicalAppointment getMedicalAppointment() {
		return medicalAppointment;
	}
}
