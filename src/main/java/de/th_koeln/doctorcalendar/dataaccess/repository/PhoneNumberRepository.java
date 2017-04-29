package de.th_koeln.doctorcalendar.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.dataaccess.entity.PhoneNumber;

public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, String> {

}
