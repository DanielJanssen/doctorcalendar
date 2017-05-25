package de.th_koeln.doctorcalendar.service.medicalappointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.application.entity.enums.MedicalAppointmentState;
import de.th_koeln.doctorcalendar.gui.user.findmedicalappointment.FindMedicalAppointmentSearchParameter;
import de.th_koeln.doctorcalendar.persistence.repository.MedicalAppointmentRepository;
import de.th_koeln.doctorcalendar.persistence.repository.MedicalAppointmentSpecification;

@SpringComponent
public class MedicalAppointmentService {

	@Autowired
	MedicalAppointmentRepository repository;

	public List<MedicalAppointment> getNextMedicalAppointments(String aLoginUserName) {
		ArrayList<MedicalAppointment> medicalAppointments = new ArrayList<>();
		medicalAppointments.addAll(repository.findByDateGreaterThanAndUserLoginName(new Date(), aLoginUserName));
		return medicalAppointments;
	}

	public void setMedicalAppointmentToFree(MedicalAppointment aMedicalAppointment) {
		aMedicalAppointment.setState(MedicalAppointmentState.FREE);
		aMedicalAppointment.setCareGiving(null);
		aMedicalAppointment.setDescription(null);
		aMedicalAppointment.setUser(null);
		repository.save(aMedicalAppointment);
	}

	public void reserveMedicalAppointment(MedicalAppointment aMedicalAppointment) {
		aMedicalAppointment.setState(MedicalAppointmentState.RESERVED);
		repository.save(aMedicalAppointment);
	}

	public List<MedicalAppointment> getPastMedicalAppointment(String aLoginUserName) {
		ArrayList<MedicalAppointment> medicalAppointments = new ArrayList<>();
		medicalAppointments.addAll(repository.findByDateBeforeAndUserLoginName(new Date(), aLoginUserName));
		return medicalAppointments;
	}

	public List<MedicalAppointment> findMedicalAppointment(FindMedicalAppointmentSearchParameter aSearchParameter) {
		MedicalAppointmentSpecification specification = new MedicalAppointmentSpecification(aSearchParameter);
		List<MedicalAppointment> medicalAppointments = repository.findAll(specification);
		return medicalAppointments;
	}

}
