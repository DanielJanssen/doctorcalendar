package de.th_koeln.doctorcalendar.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

public interface MedicalAppointmentRepository extends CrudRepository<MedicalAppointment, String> {

}
