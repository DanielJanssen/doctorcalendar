package de.th_koeln.doctorcalendar.gui.user.reservemedicalappointment;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class ReserveMedicalAppointmentModel {

	private MedicalAppointment medicalAppointment;

	public ReserveMedicalAppointmentModel(MedicalAppointment aMedicalAppointment) {
		super();
		medicalAppointment = aMedicalAppointment;
	}

	public MedicalAppointment getMedicalAppointment() {
		return medicalAppointment;
	}
}
