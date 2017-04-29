package de.th_koeln.doctorcalendar.dataaccess.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.dataaccess.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}
