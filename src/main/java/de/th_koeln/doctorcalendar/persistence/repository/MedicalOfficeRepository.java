package de.th_koeln.doctorcalendar.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.application.entity.MedicalOffice;

public interface MedicalOfficeRepository extends CrudRepository<MedicalOffice, String> {

}
