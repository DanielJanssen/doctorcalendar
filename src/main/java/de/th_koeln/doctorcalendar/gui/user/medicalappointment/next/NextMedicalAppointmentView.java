package de.th_koeln.doctorcalendar.gui.user.medicalappointment.next;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.gui.StringToDateConverter;
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
		addComponent(new NavigationComponent(controller.getLoginUser()));
		BeanItemContainer<MedicalAppointment> container = new BeanItemContainer<MedicalAppointment>(MedicalAppointment.class, model.getMedicalAppointments());
		container.addNestedContainerBean("medicalOffice");
		addComponent(getGrid(container));
		addComponent(getFooterButtons());

	}

	private Grid getGrid(BeanItemContainer<MedicalAppointment> aContainer) {
		Grid grid = new Grid(aContainer);
		grid.setSizeFull();
		grid.removeAllColumns();
		grid.addColumn("date").setHeaderCaption("Datum");
		grid.addColumn("formattedTime").setHeaderCaption("Uhrzeit");
		grid.addColumn("medicalOffice.name").setHeaderCaption("Arztpraxis");
		grid.addColumn("state").setHeaderCaption("Status");
		grid.addColumn("medicalOffice.speciality").setHeaderCaption("Fachrichtung");
		grid.addColumn("description").setHeaderCaption("Grund");
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.addSelectionListener(controller.getGridSelectionListener());

		grid.getColumn("date").setConverter(new StringToDateConverter());
		return grid;
	}

	private HorizontalLayout getFooterButtons() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.addComponent(getCancelMedicalAppointmentButton());
		horizontalLayout.addComponent(getMedicalOfficeDetailsButton());
		horizontalLayout.setSpacing(Boolean.TRUE);
		return horizontalLayout;
	}

	private Button getCancelMedicalAppointmentButton() {
		Button button = new Button("Termin absagen");
		button.addClickListener(controller.getCancelMedicalAppointmentClickListener());
		return button;
	}

	private Button getMedicalOfficeDetailsButton() {
		Button button = new Button("Arztdetails");
		button.addClickListener(controller.getMedicalOfficeDetailsClickListener());
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
