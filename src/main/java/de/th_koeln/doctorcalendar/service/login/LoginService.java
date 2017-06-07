package de.th_koeln.doctorcalendar.service.login;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;

import de.th_koeln.doctorcalendar.application.entity.User;
import de.th_koeln.doctorcalendar.persistence.repository.UserRepository;

@SpringComponent
public class LoginService {

	@Autowired
	UserRepository userRepository;

	public Boolean isCorrectLoginData(String anUsername, String aPassword) {
		User user = userRepository.findByLoginName(anUsername);
		if (user != null) {
			return user.getPassword().equals(aPassword);
		}
		return false;
	}

	public User getUser(String anUsername) {
		return userRepository.findByLoginName(anUsername);
	}
}
