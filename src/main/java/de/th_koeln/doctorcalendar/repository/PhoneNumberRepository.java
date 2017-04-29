package de.th_koeln.doctorcalendar.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.entity.PhoneNumber;

public interface PhoneNumberRepository extends CrudRepository<PhoneNumber, String> {

}
