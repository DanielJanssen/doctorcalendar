package de.th_koeln.doctorcalendar.gui.user.medicalappointment.find;

import java.sql.Time;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.ObjectProperty;
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
import de.th_koeln.doctorcalendar.application.entity.enums.Speciality;
import de.th_koeln.doctorcalendar.gui.navigation.NavigationComponent;

@SpringComponent
@VaadinSessionScope
public class FindMedicalAppointmentView extends VerticalLayout implements View {

	private static final String DATE_FORMAT = "dd.MM.yyyy";
	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "Neuen Termin finden";
	private FindMedicalAppointmentController controller;
	private FindMedicalAppointmentModel model;
	private BeanItemContainer<MedicalAppointment> container;
	private TextField medicalOfficeNameField;
	private DateField dateFrom;
	private DateField dateTo;
	private DateField timeFrom;
	private DateField timeTo;
	private ComboBox speciality;
	private TextField maximumDistance;

	@Override
	public void enter(@SuppressWarnings("unused") ViewChangeEvent aEvent) {
		removeAllComponents();
		addAllComponents();
		setSpacing(true);
	}

	private void addAllComponents() {
		addComponent(new NavigationComponent());
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
		medicalOfficeNameField = new TextField("Praxisname", model.getSearchParameter().getMedicalOfficeName());
		horizontalLayout.addComponent(medicalOfficeNameField);

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

		speciality = new ComboBox("Fachbereich");
		speciality.setNewItemsAllowed(false);
		speciality.addItems(Speciality.values());
		horizontalLayout.addComponent(speciality);

		maximumDistance = new TextField("Maximale Entfernung (km)");
		maximumDistance.setPropertyDataSource(new ObjectProperty<Integer>(model.getSearchParameter().getMaximumDistanceInKm(), Integer.class));
		horizontalLayout.addComponent(maximumDistance);

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
		grid.addColumn("medicalOffice.name").setHeaderCaption("Arztpraxis");
		grid.addColumn("medicalOffice.speciality").setHeaderCaption("Fachrichtung");
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.addSelectionListener(controller.getGridSelectionListener());
		return grid;
	}

	private Component getFooterButtons() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		Button durationsButton = new Button("Behandlungsdauern");
		durationsButton.addClickListener(controller.getDurationClickListener());
		horizontalLayout.addComponent(durationsButton);

		Button detailButton = new Button("Arztdetails");
		detailButton.addClickListener(controller.getDetailsClickListener());
		horizontalLayout.addComponent(detailButton);

		Button reserveButton = new Button("Termin reservieren");
		reserveButton.addClickListener(controller.getReserveClickListener());
		horizontalLayout.addComponent(reserveButton);
		horizontalLayout.setSpacing(true);
		return horizontalLayout;
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

	public void setItemsBeanItemContainer(List<MedicalAppointment> aMedicalAppointmentList) {
		container.removeAllItems();
		container.addAll(aMedicalAppointmentList);
	}

	public void setSearchParameterToModel() {
		FindMedicalAppointmentSearchParameter searchParameter = getModel().getSearchParameter();
		searchParameter.setMedicalOfficeName(medicalOfficeNameField.getValue());
		searchParameter.setMedicalAppointmentDateFrom(dateFrom.getValue());
		searchParameter.setMedicalAppointmentDateTo(dateTo.getValue());
		if (timeFrom.getValue() != null) {
			searchParameter.setMedicalAppointmentTimeFrom(new Time(timeFrom.getValue().getTime()));
		}
		if (timeTo.getValue() != null) {
			searchParameter.setMedicalAppointmentTimeTo(new Time(timeTo.getValue().getTime()));
		}
		searchParameter.setSpeciality((Speciality) speciality.getConvertedValue());
		if (maximumDistance.getValue() != null && maximumDistance.getValue() != "") {
			searchParameter.setMaximumDistanceInKm(Integer.valueOf(maximumDistance.getValue()));
		}
	}

}
