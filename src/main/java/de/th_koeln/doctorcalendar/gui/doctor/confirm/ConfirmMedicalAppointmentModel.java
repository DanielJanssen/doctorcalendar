package de.th_koeln.doctorcalendar.gui.doctor.confirm;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class ConfirmMedicalAppointmentModel {

	private MedicalAppointment medicalAppointment;

	public ConfirmMedicalAppointmentModel(MedicalAppointment aMedicalAppointment) {
		super();
		medicalAppointment = aMedicalAppointment;
	}

	public MedicalAppointment getMedicalAppointment() {
		return medicalAppointment;
	}
}