package de.th_koeln.doctorcalendar.gui.user.medicalappointment.past;

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
public class PastMedicalAppointmentView extends VerticalLayout implements View {

	private static final long serialVersionUID = 1L;

	public static final String VIEW_NAME = "Vergangene Termine";

	PastMedicalAppointmentController controller;

	private PastMedicalAppointmentModel model;

	@Override
	public void enter(ViewChangeEvent aEvent) {
		controller.findPastMedicalAppointments();
		addAllComponents();
		setSpacing(true);
	}

	private void addAllComponents() {
		removeAllComponents();
		addComponent(new NavigationComponent());
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
		Button button = new Button("Neuer Termin bei Arzt");
		button.addClickListener(controller.getNewMedicalAppointmentClickListener());
		return button;
	}

	private Button getMedicalOfficeDetailsButton() {
		Button button = new Button("Arztdetails");
		button.addClickListener(controller.getMedicalOfficeDetailsClickListener());
		return button;
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

	public String getUserName() {
		return getSession().getAttribute("user").toString();
	}

}
