package de.th_koeln.doctorcalendar.service.medicalappointment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.application.entity.MedicalOfficeUserDistance;
import de.th_koeln.doctorcalendar.application.entity.User;
import de.th_koeln.doctorcalendar.application.entity.enums.MedicalAppointmentState;
import de.th_koeln.doctorcalendar.gui.doctor.find.FindDoctorSearchParameter;
import de.th_koeln.doctorcalendar.gui.user.medicalappointment.find.FindMedicalAppointmentSearchParameter;
import de.th_koeln.doctorcalendar.persistence.repository.MedicalAppointmentRepository;
import de.th_koeln.doctorcalendar.persistence.repository.MedicalAppointmentSpecification;
import de.th_koeln.doctorcalendar.persistence.repository.MedicalAppointmentSpecificationParameter;
import de.th_koeln.doctorcalendar.persistence.repository.UserRepository;

@SpringComponent
public class MedicalAppointmentService {

	@Autowired
	MedicalAppointmentRepository repository;

	@Autowired
	UserRepository userRepository;

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

	public void reserveMedicalAppointment(MedicalAppointment aMedicalAppointment, String aUserName) {
		aMedicalAppointment.setState(MedicalAppointmentState.RESERVED);
		aMedicalAppointment.setUser(userRepository.findByLoginName(aUserName));
		repository.save(aMedicalAppointment);
	}

	public List<MedicalAppointment> getPastMedicalAppointment(String aLoginUserName) {
		ArrayList<MedicalAppointment> medicalAppointments = new ArrayList<>();
		medicalAppointments.addAll(repository.findByDateBeforeAndUserLoginName(new Date(), aLoginUserName));
		return medicalAppointments;
	}

	public List<MedicalAppointment> findMedicalAppointment(FindMedicalAppointmentSearchParameter aSearchParameter) {
		MedicalAppointmentSpecification specification = new MedicalAppointmentSpecification(new MedicalAppointmentSpecificationParameter(aSearchParameter));
		List<MedicalAppointment> medicalAppointments = repository.findAll(specification);
		List<MedicalAppointment> toDeleteMedicalAppointments = new ArrayList<>();
		User aUser = userRepository.findByLoginName(aSearchParameter.getUserName());
		for (MedicalAppointment tempMedicalAppointment : medicalAppointments) {
			for (MedicalOfficeUserDistance tempDistance : aUser.getDistances()) {
				if (tempMedicalAppointment.getMedicalOffice().equals(tempDistance.getMedicalOffice())) {
					tempMedicalAppointment.setDistance(tempDistance.getDistance());
					if (aSearchParameter.getMaximumDistanceInKm() != null) {
						if (aSearchParameter.getMaximumDistanceInKm() < tempDistance.getDistance()) {
							toDeleteMedicalAppointments.add(tempMedicalAppointment);
						}
					}
				}
			}
		}
		medicalAppointments.removeAll(toDeleteMedicalAppointments);
		return medicalAppointments;
	}

	public List<MedicalAppointment> findMedicalAppointment(FindDoctorSearchParameter aSearchParameter, String aUserName) {
		User user = userRepository.findByLoginName(aUserName);
		MedicalAppointmentSpecification specification = new MedicalAppointmentSpecification(
				new MedicalAppointmentSpecificationParameter(aSearchParameter, user.getWorkingMedicalOffice()));
		List<MedicalAppointment> medicalAppointments = repository.findAll(specification);
		return medicalAppointments;
	}

	public void setMedicalAppointmentToAccepted(MedicalAppointment aMedicalAppointment) {
		aMedicalAppointment.setState(MedicalAppointmentState.ACCEPTED);
		repository.save(aMedicalAppointment);
	}

}
