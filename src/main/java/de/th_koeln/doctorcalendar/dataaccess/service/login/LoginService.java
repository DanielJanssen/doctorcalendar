package de.th_koeln.doctorcalendar.dataaccess.service.login;

import org.springframework.beans.factory.annotation.Autowired;

import de.th_koeln.doctorcalendar.dataaccess.entity.User;
import de.th_koeln.doctorcalendar.dataaccess.repository.UserRepository;

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
}
