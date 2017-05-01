package de.th_koeln.doctorcalendar.persistence.repository;

import org.springframework.data.repository.CrudRepository;

import de.th_koeln.doctorcalendar.application.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

	public User findByLoginName(String aLoginName);
}
