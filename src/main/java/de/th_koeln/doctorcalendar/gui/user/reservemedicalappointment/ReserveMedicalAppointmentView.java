package de.th_koeln.doctorcalendar.gui.user.reservemedicalappointment;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import de.th_koeln.doctorcalendar.application.entity.Caregiving;

@SpringComponent
@VaadinSessionScope
public class ReserveMedicalAppointmentView extends Window {

	private static final long serialVersionUID = 1L;
	private ReserveMedicalAppointmentController controller;
	private ReserveMedicalAppointmentModel model;

	private ComboBox caregiving;
	private TextField description;

	public ReserveMedicalAppointmentView() {

		super("Termin reservieren?");
	}

	public void enter() {
		center();
		setModal(true);
		setClosable(false);
		setDraggable(false);

		VerticalLayout content = new VerticalLayout();
		content.addComponent(getHeaderCaption());
		content.addComponent(getDateLayout());
		content.addComponent(getMedicalOffice());
		content.addComponent(getSpeciality());
		content.addComponent(getMedicalAppointmentDescription());
		content.addComponent(getCaregiving());
		content.addComponent(getButtons());
		content.setMargin(true);

		setContent(content);
	}

	private Label getHeaderCaption() {
		return new Label("Möchten Sie den folgenden Termin wirklich verbindlich reservieren?");
	}

	private HorizontalLayout getDateLayout() {
		HorizontalLayout dateLayout = new HorizontalLayout();
		TextField dateTextField = new TextField("Datum");
		dateTextField.setValue(model.getMedicalAppointment().getDate().toString());
		dateTextField.setEnabled(false);
		dateLayout.addComponent(dateTextField);

		TextField timeTextField = new TextField("Uhrzeit");
		timeTextField.setValue(model.getMedicalAppointment().getTimeFrom().toString() + " - " + model.getMedicalAppointment().getTimeTo().toString());
		timeTextField.setEnabled(false);
		dateLayout.addComponent(timeTextField);
		return dateLayout;
	}

	private TextField getMedicalOffice() {
		TextField textField = new TextField("Arztpraxis");
		textField.setValue(model.getMedicalAppointment().getMedicalOffice().toString());
		textField.setEnabled(false);
		textField.setWidth("100%");
		return textField;
	}

	private TextField getSpeciality() {
		TextField textField = new TextField("Fachrichtung");
		textField.setValue(model.getMedicalAppointment().getMedicalOffice().getSpeciality().getGermanDescription());
		textField.setEnabled(false);
		textField.setWidth("100%");
		return textField;
	}

	private TextField getMedicalAppointmentDescription() {
		description = new TextField("Grund des Besuches");
		description.setWidth("100%");
		return description;
	}

	private ComboBox getCaregiving() {
		caregiving = new ComboBox("Behandlung");
		caregiving.setNewItemsAllowed(false);
		caregiving.addItems(getModel().getCaregivings());
		caregiving.setWidth("100%");
		return caregiving;
	}

	private HorizontalLayout getButtons() {
		HorizontalLayout layout = new HorizontalLayout();

		Button cancel = new Button("Abbrechen");
		cancel.addClickListener(controller.getCancelCloseListener());
		layout.addComponent(cancel);
		layout.setComponentAlignment(cancel, Alignment.BOTTOM_LEFT);

		Button ok = new Button("Ok");
		ok.addClickListener(controller.getOkCloseListener());
		layout.addComponent(ok);
		layout.setComponentAlignment(ok, Alignment.BOTTOM_RIGHT);

		layout.setMargin(true);
		layout.setSpacing(true);
		return layout;
	}

	public void setController(ReserveMedicalAppointmentController aController) {
		controller = aController;
	}

	public ReserveMedicalAppointmentModel getModel() {
		return model;
	}

	public void setModel(ReserveMedicalAppointmentModel aModel) {
		model = aModel;
	}

	public String getUserName() {
		return getSession().getAttribute("user").toString();
	}

	public void setUserInput() {
		if (caregiving.getValue() != null) {
			getModel().getMedicalAppointment().setCareGiving((Caregiving) caregiving.getValue());
		}
		getModel().getMedicalAppointment().setDescription(description.getValue());
	}

}
