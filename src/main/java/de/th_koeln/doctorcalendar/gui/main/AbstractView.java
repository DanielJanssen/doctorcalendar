package de.th_koeln.doctorcalendar.gui.main;

import com.vaadin.navigator.View;
import com.vaadin.ui.VerticalLayout;

public abstract class AbstractView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public String getUserName() {
		return getSession().getAttribute("user").toString();
	}

}
