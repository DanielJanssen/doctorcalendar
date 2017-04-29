package de.th_koeln.doctorcalendar.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.dataaccess.entity.MedicalAppointment;

public interface MedicalAppointmentRepository extends CrudRepository<MedicalAppointment, String> {

}
