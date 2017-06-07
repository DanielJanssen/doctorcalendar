package de.th_koeln.doctorcalendar.gui.navigation;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import de.th_koeln.doctorcalendar.application.entity.User;
import de.th_koeln.doctorcalendar.gui.doctor.find.FindDoctorView;
import de.th_koeln.doctorcalendar.gui.user.medicalappointment.find.FindMedicalAppointmentView;
import de.th_koeln.doctorcalendar.gui.user.medicalappointment.next.NextMedicalAppointmentView;
import de.th_koeln.doctorcalendar.gui.user.medicalappointment.past.PastMedicalAppointmentView;

@SpringComponent
@VaadinSessionScope
public class NavigationComponent extends HorizontalLayout {

	private static final long serialVersionUID = 1L;

	// TODO rt57, 02.06.2017: define visibility for patient/medical office
	public NavigationComponent(User aUser) {
		MenuBar menuBar = new MenuBar();
		addComponent(menuBar);
		Command navigation = getMenuBarCommand();
		MenuItem medicalAppointment = menuBar.addItem("Terminübersicht", null);
		medicalAppointment.addItem(NextMedicalAppointmentView.VIEW_NAME, navigation);
		medicalAppointment.addItem(PastMedicalAppointmentView.VIEW_NAME, navigation);
		medicalAppointment.addItem(FindMedicalAppointmentView.VIEW_NAME, navigation);
		medicalAppointment.setVisible(aUser.getWorkingMedicalOffice() == null);

		MenuItem medicalOfficeView = menuBar.addItem("Terminübersicht", null);
		medicalOfficeView.addItem(FindDoctorView.VIEW_NAME, navigation);
		medicalOfficeView.setVisible(aUser.getWorkingMedicalOffice() != null);
	}

	private Command getMenuBarCommand() {
		return new Command() {
			private static final long serialVersionUID = 1L;

			@Override
			public void menuSelected(MenuItem selectedItem) {
				Notification.show(selectedItem.getText());
				UI.getCurrent().getNavigator().navigateTo(selectedItem.getText());
			}
		};
	}
}
