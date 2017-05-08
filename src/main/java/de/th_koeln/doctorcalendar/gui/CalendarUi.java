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
import de.th_koeln.doctorcalendar.gui.user.findmedicalappointment.FindMedicalAppointmentController;
import de.th_koeln.doctorcalendar.gui.user.findmedicalappointment.FindMedicalAppointmentView;
import de.th_koeln.doctorcalendar.gui.user.nextmedicalappointment.NextMedicalAppointmentController;
import de.th_koeln.doctorcalendar.gui.user.nextmedicalappointment.NextMedicalAppointmentView;
import de.th_koeln.doctorcalendar.gui.user.pastmedicalappointment.PastMedicalAppointmentController;
import de.th_koeln.doctorcalendar.gui.user.pastmedicalappointment.PastMedicalAppointmentView;

@SpringUI
@Theme("valo")
public class CalendarUi extends UI {

	private static final long serialVersionUID = 1L;

	@Autowired
	LoginView loginView;

	@Autowired
	NextMedicalAppointmentController nextMedicalAppointmentController;

	@Autowired
	PastMedicalAppointmentController pastMedicalApointmentController;

	@Autowired
	FindMedicalAppointmentController findMedicalAppointmentController;

	@Autowired
	private SpringViewProvider viewProvider;

	private Navigator navigator;

	@Override
	protected void init(@SuppressWarnings("unused") VaadinRequest aRequest) {
		getPage().setTitle("Ärzteterminkalender - Finde deinen nächsten Termin");
		navigator = new Navigator(this, this);
		navigator.addView(LoginView.VIEW_NAME, loginView);
		navigator.addView(NextMedicalAppointmentView.VIEW_NAME, nextMedicalAppointmentController.getView());
		navigator.addView(PastMedicalAppointmentView.VIEW_NAME, pastMedicalApointmentController.getView());
		navigator.addView(FindMedicalAppointmentView.VIEW_NAME, findMedicalAppointmentController.getView());
		navigator.addProvider(viewProvider);
		navigator.addViewChangeListener(createViewChangeListener());
	}

	private ViewChangeListener createViewChangeListener() {
		return new ViewChangeListener() {
			private static final long serialVersionUID = 1L;

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
			public void afterViewChange(@SuppressWarnings("unused") ViewChangeEvent aEvent) {
			}
		};
	}
}
