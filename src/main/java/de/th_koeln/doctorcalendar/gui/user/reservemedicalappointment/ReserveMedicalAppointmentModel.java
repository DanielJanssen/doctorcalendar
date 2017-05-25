package de.th_koeln.doctorcalendar.gui.user.reservemedicalappointment;

import java.util.List;

import de.th_koeln.doctorcalendar.application.entity.Caregiving;
import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public class ReserveMedicalAppointmentModel {

	private MedicalAppointment medicalAppointment;
	private List<Caregiving> caregivings;

	public ReserveMedicalAppointmentModel(MedicalAppointment aMedicalAppointment, List<Caregiving> someCaregivings) {
		super();
		medicalAppointment = aMedicalAppointment;
		caregivings = someCaregivings;
	}

	public MedicalAppointment getMedicalAppointment() {
		return medicalAppointment;
	}

	public List<Caregiving> getCaregivings() {
		return caregivings;
	}
}
