package de.th_koeln.doctorcalendar.gui.main;

import org.springframework.beans.factory.annotation.Autowired;

import de.th_koeln.doctorcalendar.application.entity.User;
import de.th_koeln.doctorcalendar.persistence.repository.UserRepository;

public abstract class AbstractController {

	@Autowired
	UserRepository userRepository;

	public User getLoginUser() {
		return userRepository.findByLoginName(getView().getUserName());
	}

	public abstract AbstractView getView();
}
