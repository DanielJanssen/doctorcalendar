package de.th_koeln.doctorcalendar.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public interface MedicalAppointmentRepository extends CrudRepository<MedicalAppointment, String> {

	// TODO rt57, 03.05.2017: größer gleich heute am besten
	public List<MedicalAppointment> findByDateGreaterThanAndUserLoginName(Date aDate, String aLoginName);
}
