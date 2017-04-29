package de.th_koeln.doctorcalendar.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.entity.Address;

public interface AddressRepository extends CrudRepository<Address, String> {

}
