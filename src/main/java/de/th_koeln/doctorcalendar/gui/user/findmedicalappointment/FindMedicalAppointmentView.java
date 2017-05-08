package de.th_koeln.doctorcalendar.gui.user.findmedicalappointment;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.th_koeln.doctorcalendar.gui.navigation.NavigationComponent;

@SpringComponent
@VaadinSessionScope
public class FindMedicalAppointmentView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "Neuen Termin finden";
	private FindMedicalAppointmentController controller;
	private FindMedicalAppointmentModel model;

	@Override
	public void enter(@SuppressWarnings("unused") ViewChangeEvent aEvent) {
		removeAllComponents();
		addAllComponents();
		setSpacing(true);
	}

	private void addAllComponents() {
		addComponent(new NavigationComponent());
		addComponent(getSearchCriteria());
	}

	private HorizontalLayout getSearchCriteria() {
		HorizontalLayout tempHorizontalLayout = new HorizontalLayout();
		tempHorizontalLayout.addComponent(new TextField("Test"));
		return tempHorizontalLayout;
	}

	public FindMedicalAppointmentModel getModel() {
		return model;
	}

	public void setModel(FindMedicalAppointmentModel aModel) {
		model = aModel;
	}

	public void setController(FindMedicalAppointmentController aController) {
		controller = aController;
	}

}
