package de.th_koeln.doctorcalendar.gui.user.findmedicalappointment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;

@SpringComponent
@VaadinSessionScope
public class FindMedicalAppointmentController {

	@Autowired
	FindMedicalAppointmentView view;

	@PostConstruct
	public void init() {
		getView().setController(this);
	}

	public FindMedicalAppointmentView getView() {
		return view;
	}
}
