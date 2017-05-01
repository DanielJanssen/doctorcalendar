package de.th_koeln.doctorcalendar.gui;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;

import de.th_koeln.doctorcalendar.gui.login.LoginView;
import de.th_koeln.doctorcalendar.gui.user.NextMedicalAppointmentController;
import de.th_koeln.doctorcalendar.gui.user.NextMedicalAppointmentView;

@SpringUI
@Theme("valo")
public class CalendarUi extends UI {

	@Autowired
	LoginView loginView;

	@Autowired
	NextMedicalAppointmentController nextMedicalAppointmentController;

	@Autowired
	private SpringViewProvider viewProvider;

	private Navigator navigator;

	@Override
	protected void init(VaadinRequest aRequest) {
		getPage().setTitle("Ärzteterminkalender - Finde deinen nächsten Termin");
		navigator = new Navigator(this, this);
		navigator.addView(LoginView.VIEW_NAME, loginView);
		navigator.addView(NextMedicalAppointmentView.VIEW_NAME, nextMedicalAppointmentController.getView());
		navigator.addProvider(viewProvider);
		navigator.addViewChangeListener(createViewChangeListener());
	}

	private ViewChangeListener createViewChangeListener() {
		return new ViewChangeListener() {
			@Override
			public boolean beforeViewChange(ViewChangeEvent anEvent) {
				// Check if a user has logged in
				boolean isLoggedIn = getSession().getAttribute("user") != null;
				boolean isLoginView = anEvent.getNewView() instanceof LoginView;

				if (!isLoggedIn && !isLoginView) {
					getNavigator().navigateTo(LoginView.VIEW_NAME);
					return false;
				} else if (isLoggedIn && isLoginView) {
					return false;
				}
				return true;
			}

			@Override
			public void afterViewChange(ViewChangeEvent aEvent) {
			}
		};
	}
}
