package de.th_koeln.doctorcalendar.gui.user.pastmedicalappointment;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.VerticalLayout;

import de.th_koeln.doctorcalendar.gui.navigation.NavigationComponent;

@SpringComponent
@VaadinSessionScope
public class PastMedicalAppointmentView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "Vergangene Termine";

	PastMedicalAppointmentController controller;

	private PastMedicalAppointmentModel model;

	@Override
	public void enter(ViewChangeEvent aEvent) {
		//		controller.findPastMedicalAppointments();
		addAllComponents();
		setSpacing(true);
	}

	private void addAllComponents() {
		removeAllComponents();
		addComponent(new NavigationComponent());
	}

	public PastMedicalAppointmentModel getModel() {
		return model;
	}

	public void setModel(PastMedicalAppointmentModel aModel) {
		model = aModel;
	}

	public void setController(PastMedicalAppointmentController aController) {
		controller = aController;
	}

}
