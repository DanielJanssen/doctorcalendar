package de.th_koeln.doctorcalendar.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}
