package de.th_koeln.doctorcalendar.gui.doctor.find;

import java.sql.Time;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.application.entity.enums.MedicalAppointmentState;
import de.th_koeln.doctorcalendar.gui.StringToDateConverter;
import de.th_koeln.doctorcalendar.gui.navigation.NavigationComponent;

@SpringComponent
@VaadinSessionScope
public class FindDoctorView extends VerticalLayout implements View {

	private static final String DATE_FORMAT = "dd.MM.yyyy";
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "Arzt - Terminübersicht";
	private FindDoctorController controller;
	private FindDoctorModel model;
	private BeanItemContainer<MedicalAppointment> container;
	private TextField patientNameField;
	private DateField dateFrom;
	private DateField dateTo;
	private DateField timeFrom;
	private DateField timeTo;
	private ComboBox state;

	@Override
	public void enter(@SuppressWarnings("unused") ViewChangeEvent aEvent) {
		removeAllComponents();
		addAllComponents();
		setSpacing(true);
	}

	private void addAllComponents() {
		addComponent(new NavigationComponent(controller.getLoginUser()));
		addComponent(getSearchPanel());
		container = new BeanItemContainer<MedicalAppointment>(MedicalAppointment.class, model.getMedicalAppointments());
		container.addNestedContainerBean("medicalOffice");

		addComponent(getGrid(container));
		addComponent(getFooterButtons());
	}

	private Panel getSearchPanel() {
		Panel searchPanel = new Panel();
		searchPanel.setCaption("Suchkriterien");
		VerticalLayout verticalLayout = new VerticalLayout();
		verticalLayout.setSpacing(true);

		verticalLayout.addComponent(getSearchCriteria());
		verticalLayout.addComponent(getButtonsForSearchPanel());
		searchPanel.setContent(verticalLayout);
		return searchPanel;
	}

	private HorizontalLayout getSearchCriteria() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		dateFrom = new DateField("Termin (von)", model.getSearchParameter().getMedicalAppointmentDateFrom());
		dateFrom.setDateFormat(DATE_FORMAT);
		horizontalLayout.addComponent(dateFrom);

		dateTo = new DateField("Termin (bis)", model.getSearchParameter().getMedicalAppointmentDateTo());
		dateTo.setDateFormat(DATE_FORMAT);
		horizontalLayout.addComponent(dateTo);

		timeFrom = new DateField("Uhrzeit (von)", model.getSearchParameter().getMedicalAppointmentTimeFrom());
		timeFrom.setResolution(Resolution.MINUTE);
		timeFrom.setDateFormat("HH:mm");
		horizontalLayout.addComponent(timeFrom);

		timeTo = new DateField("Uhrzeit (bis)", model.getSearchParameter().getMedicalAppointmentDateTo());
		timeTo.setResolution(Resolution.MINUTE);
		timeTo.setDateFormat("HH:mm");
		horizontalLayout.addComponent(timeTo);

		patientNameField = new TextField("Patientenname", model.getSearchParameter().getPatientName());
		patientNameField.setNullRepresentation("");
		horizontalLayout.addComponent(patientNameField);

		state = new ComboBox("Status");
		state.setNewItemsAllowed(false);
		state.addItems(MedicalAppointmentState.values());
		horizontalLayout.addComponent(state);

		horizontalLayout.setSpacing(true);

		return horizontalLayout;
	}

	private HorizontalLayout getButtonsForSearchPanel() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		Button resetButton = new Button("Kriterien zurücksetzen");
		resetButton.addClickListener(controller.getResetClickListener());
		horizontalLayout.addComponent(resetButton);

		Button searchButton = new Button("Suche");
		searchButton.addClickListener(controller.getSearchClickListener());

		horizontalLayout.addComponent(searchButton);
		horizontalLayout.setSpacing(true);
		return horizontalLayout;
	}

	private Grid getGrid(BeanItemContainer<MedicalAppointment> aContainer) {
		Grid grid = new Grid(aContainer);
		grid.setSizeFull();
		grid.removeAllColumns();
		grid.addColumn("date").setHeaderCaption("Datum");
		grid.addColumn("formattedTime").setHeaderCaption("Uhrzeit");
		grid.addColumn("user").setHeaderCaption("Patient");
		grid.addColumn("careGiving").setHeaderCaption("Grund");
		grid.addColumn("state").setHeaderCaption("Status");

		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.addSelectionListener(controller.getGridSelectionListener());

		grid.getColumn("date").setConverter(new StringToDateConverter());
		return grid;
	}

	private Component getFooterButtons() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		Button cancelMedicalAppointmentButton = new Button("Termin absagen");
		cancelMedicalAppointmentButton.addClickListener(controller.getCancelMedicalAppointmentClickListener());
		horizontalLayout.addComponent(cancelMedicalAppointmentButton);

		Button offerMedicalAppointmentButton = new Button("Termine anbieten");
		offerMedicalAppointmentButton.addClickListener(controller.getOfferMedicalAppointmentsClickListener());
		horizontalLayout.addComponent(offerMedicalAppointmentButton);

		Button editMedicalAppointmentButton = new Button("Termin bearbeiten");
		editMedicalAppointmentButton.addClickListener(controller.getEditMedicalAppointmentClickListener());
		horizontalLayout.addComponent(editMedicalAppointmentButton);

		Button patientDetailsButton = new Button("Patientendetails");
		patientDetailsButton.addClickListener(controller.getPatientDetailsClickListener());
		horizontalLayout.addComponent(patientDetailsButton);

		Button confirmMedicalAppointmentButton = new Button("Termin bestätigen");
		confirmMedicalAppointmentButton.addClickListener(controller.getConfirmMedicalAppointmentClickListener());
		horizontalLayout.addComponent(confirmMedicalAppointmentButton);

		horizontalLayout.setSpacing(true);
		return horizontalLayout;
	}

	public FindDoctorModel getModel() {
		return model;
	}

	public void setModel(FindDoctorModel aModel) {
		model = aModel;
	}

	public String getUserName() {
		return getSession().getAttribute("user").toString();
	}

	public void setController(FindDoctorController aController) {
		controller = aController;
	}

	public void setItemsBeanItemContainer(List<MedicalAppointment> aMedicalAppointmentList) {
		container.removeAllItems();
		container.addAll(aMedicalAppointmentList);
	}

	public void setSearchParameterToModel() {
		FindDoctorSearchParameter searchParameter = getModel().getSearchParameter();
		searchParameter.setPatientName(patientNameField.getValue());
		searchParameter.setMedicalAppointmentDateFrom(dateFrom.getValue());
		searchParameter.setMedicalAppointmentDateTo(dateTo.getValue());
		if (timeFrom.getValue() != null) {
			searchParameter.setMedicalAppointmentTimeFrom(new Time(timeFrom.getValue().getTime()));
		}
		if (timeTo.getValue() != null) {
			searchParameter.setMedicalAppointmentTimeTo(new Time(timeTo.getValue().getTime()));
		}
		searchParameter.setState((MedicalAppointmentState) state.getConvertedValue());
	}

}
