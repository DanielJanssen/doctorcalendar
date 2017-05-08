package de.th_koeln.doctorcalendar.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public interface MedicalAppointmentRepository extends CrudRepository<MedicalAppointment, String> {

	public List<MedicalAppointment> findByDateGreaterThanAndUserLoginName(Date aDate, String aLoginName);

	public List<MedicalAppointment> findByDateBeforeAndUserLoginName(Date aDate, String aLoginName);
}
