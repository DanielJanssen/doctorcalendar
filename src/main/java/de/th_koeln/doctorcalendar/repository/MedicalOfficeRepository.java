package de.th_koeln.doctorcalendar.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.entity.MedicalOffice;

public interface MedicalOfficeRepository extends CrudRepository<MedicalOffice, String> {

}
