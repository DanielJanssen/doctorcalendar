package de.th_koeln.doctorcalendar.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.dataaccess.entity.MedicalOffice;

public interface MedicalOfficeRepository extends CrudRepository<MedicalOffice, String> {

}
