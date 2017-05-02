package de.th_koeln.doctorcalendar.gui.user;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.gui.navigation.NavigationComponent;

@SpringComponent
@VaadinSessionScope
public class NextMedicalAppointmentView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "Kommende Termine";

	private NextMedicalAppointmentModel model;

	private NextMedicalAppointmentController controller;

	@Override
	public void enter(ViewChangeEvent aEvent) {
		controller.findNextMedicalAppointments();
		addComponent(new NavigationComponent());
		addAllComponents();
		setSpacing(true);
	}

	private void addAllComponents() {
		BeanItemContainer<MedicalAppointment> container = new BeanItemContainer<MedicalAppointment>(MedicalAppointment.class, model.getMedicalAppointments());
		container.addNestedContainerBean("medicalOffice");
		Grid grid = new Grid(container);
		grid.setSizeFull();
		grid.removeAllColumns();
		grid.addColumn("date").setHeaderCaption("Datum");
		grid.addColumn("formattedTime").setHeaderCaption("Uhrzeit");
		grid.addColumn("medicalOffice.name").setHeaderCaption("Arztpraxis");
		grid.addColumn("medicalOffice.speciality").setHeaderCaption("Fachrichtung");
		grid.addColumn("description").setHeaderCaption("Grund");

		addComponent(grid);
	}

	public NextMedicalAppointmentModel getModel() {
		return model;
	}

	public void setModel(NextMedicalAppointmentModel aModel) {
		model = aModel;
	}

	public String getUserName() {
		return getSession().getAttribute("user").toString();
	}

	public void setController(NextMedicalAppointmentController aController) {
		controller = aController;
	}

}
