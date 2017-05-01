package de.th_koeln.doctorcalendar.service.medicalappointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.spring.annotation.SpringComponent;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

@SpringComponent
public class MedicalAppointmentService {

	public List<MedicalAppointment> getNextMedicalAppointments(String aLoginUserName) {
		ArrayList<MedicalAppointment> medicalAppointments = new ArrayList<>();
		mockMedicalAppointments(medicalAppointments);
		return medicalAppointments;
	}

	private void mockMedicalAppointments(ArrayList<MedicalAppointment> aMedicalAppointments) {
		MedicalAppointment tempMedicalAppointment = new MedicalAppointment();
		tempMedicalAppointment.setDate(new Date());
		aMedicalAppointments.add(tempMedicalAppointment);
	}
}
