package de.th_koeln.doctorcalendar.gui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;

import de.th_koeln.doctorcalendar.gui.login.Login;

@SpringUI
@Theme("valo")
public class CalendarUi extends UI {

	@Autowired
	Login login;

	private Navigator navigator;

	@Override
	protected void init(VaadinRequest aRequest) {
		getPage().setTitle("Ärzteterminkalender - Finde deinen nächsten Termin");
		navigator = new Navigator(this, this);
		navigator.addView("", login);
	}

}
