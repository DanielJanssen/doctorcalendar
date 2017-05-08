package de.th_koeln.doctorcalendar.gui.user.findmedicalappointment;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

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

	private Panel getSearchCriteria() {
		Panel searchPanel = new Panel();
		searchPanel.setCaption("Suchkriterien");
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		TextField medicalOfficeName = new TextField("Praxisname", model.getSearchParameter().getMedicalOfficeName());
		horizontalLayout.addComponent(medicalOfficeName);

		DateField dateFrom = new DateField("Termin (von)", model.getSearchParameter().getMedicalAppointmentDateFrom());
		dateFrom.setDateFormat(DATE_FORMAT);
		horizontalLayout.addComponent(dateFrom);

		DateField dateTo = new DateField("Termin (bis)", model.getSearchParameter().getMedicalAppointmentDateTo());
		dateTo.setDateFormat(DATE_FORMAT);
		horizontalLayout.addComponent(dateTo);

		DateField timeFrom = new DateField("Uhrzeit (von)", model.getSearchParameter().getMedicalAppointmentTimeFrom());
		timeFrom.setResolution(Resolution.MINUTE);
		timeFrom.setDateFormat("HH:mm");
		horizontalLayout.addComponent(timeFrom);

		DateField timeTo = new DateField("Uhrzeit (bis)", model.getSearchParameter().getMedicalAppointmentDateTo());
		timeTo.setResolution(Resolution.MINUTE);
		timeTo.setDateFormat("HH:mm");
		horizontalLayout.addComponent(timeTo);

		ComboBox speciality = new ComboBox("Fachbereich");
		speciality.setNewItemsAllowed(false);
		speciality.addItems(Speciality.values());
		horizontalLayout.addComponent(speciality);

		TextField maximumDistance = new TextField("Maximale Entfernung (km)");
		maximumDistance.setPropertyDataSource(new ObjectProperty<Integer>(model.getSearchParameter().getMaximumDistanceInKm(), Integer.class));
		horizontalLayout.addComponent(maximumDistance);

		horizontalLayout.setSpacing(true);
		searchPanel.setContent(horizontalLayout);
		return searchPanel;
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
