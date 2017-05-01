package de.th_koeln.doctorcalendar.gui.user;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
@VaadinSessionScope
public class NextMedicalAppointment extends VerticalLayout implements View {

	public static final String VIEW_NAME = "Next Medical Appointment";

	@Override
	public void enter(ViewChangeEvent aEvent) {
	}

}
