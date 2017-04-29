package de.th_koeln.doctorcalendar.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.entity.MedicalAppointment;

public interface MedicalAppointmentRepository extends CrudRepository<MedicalAppointment, String> {

}
