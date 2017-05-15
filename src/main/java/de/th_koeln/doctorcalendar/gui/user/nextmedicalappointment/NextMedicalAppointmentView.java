package de.th_koeln.doctorcalendar.gui.user.nextmedicalappointment;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
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
	public void enter(@SuppressWarnings("unused") ViewChangeEvent anEvent) {
		removeAllComponents();
		controller.findNextMedicalAppointments();
		addAllComponents();
		setSpacing(true);
	}

	private void addAllComponents() {
		addComponent(new NavigationComponent());
		BeanItemContainer<MedicalAppointment> container = new BeanItemContainer<MedicalAppointment>(MedicalAppointment.class, model.getMedicalAppointments());
		container.addNestedContainerBean("medicalOffice");
		addComponent(getGrid(container));
		Component tempCancelMedicalAppointmentButton = getCancelMedicalAppointmentButton();
		addComponent(tempCancelMedicalAppointmentButton);
		setComponentAlignment(tempCancelMedicalAppointmentButton, Alignment.MIDDLE_LEFT);

	}

	private Grid getGrid(BeanItemContainer<MedicalAppointment> aContainer) {
		Grid grid = new Grid(aContainer);
		grid.setSizeFull();
		grid.removeAllColumns();
		grid.addColumn("date").setHeaderCaption("Datum");
		grid.addColumn("formattedTime").setHeaderCaption("Uhrzeit");
		grid.addColumn("medicalOffice.name").setHeaderCaption("Arztpraxis");
		grid.addColumn("medicalOffice.speciality").setHeaderCaption("Fachrichtung");
		grid.addColumn("description").setHeaderCaption("Grund");
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.addSelectionListener(controller.getGridSelectionListener());
		return grid;
	}

	private Component getCancelMedicalAppointmentButton() {
		Button button = new Button("Termin absagen");
		button.addClickListener(controller.getCancelMedicalAppointmentClickListener());
		return button;
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
