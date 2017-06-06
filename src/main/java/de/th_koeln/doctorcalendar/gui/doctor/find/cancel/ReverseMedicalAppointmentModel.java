package de.th_koeln.doctorcalendar.gui.doctor.find.cancel;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class ReverseMedicalAppointmentModel {

	private MedicalAppointment medicalAppointment;
	private String reasonOfReserve;

	public ReverseMedicalAppointmentModel(MedicalAppointment aMedicalAppointment) {
		super();
		medicalAppointment = aMedicalAppointment;
	}

	public MedicalAppointment getMedicalAppointment() {
		return medicalAppointment;
	}

	public String getReasonOfReserve() {
		return reasonOfReserve;
	}

	public void setReasonOfReserve(String aReasonOfReserve) {
		reasonOfReserve = aReasonOfReserve;
	}

}
