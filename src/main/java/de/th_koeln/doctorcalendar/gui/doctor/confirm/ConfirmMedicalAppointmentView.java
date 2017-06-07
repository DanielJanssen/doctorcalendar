package de.th_koeln.doctorcalendar.gui.doctor.confirm;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SpringComponent
@VaadinSessionScope
public class ConfirmMedicalAppointmentView extends Window {

	private static final long serialVersionUID = 1L;
	private ConfirmMedicalAppointmentController controller;
	private ConfirmMedicalAppointmentModel model;

	private TextField reasonForReverse;

	public ConfirmMedicalAppointmentView() {
		super("Termin bestätigen?");
	}

	public void enter() {
		center();
		setModal(true);
		setClosable(false);
		setDraggable(false);

		VerticalLayout content = new VerticalLayout();
		content.addComponent(getHeaderCaption());
		content.addComponent(getDateLayout());
		content.addComponent(getPatient());
		content.addComponent(getCaregiving());
		content.addComponent(getMedicalAppointmentDescription());
		content.addComponent(new Label("Der Patient wird über die Bestätigung per Mail informiert"));
		content.addComponent(getButtons());

		content.setMargin(true);

		setContent(content);
	}

	private Label getHeaderCaption() {
		return new Label("Möchten Sie den folgenden Termin für den Patienten verbindlich bestätigen?");
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

	private TextField getPatient() {
		TextField textField = new TextField("Patient");
		textField.setValue(model.getMedicalAppointment().getUser().toString());
		textField.setEnabled(false);
		textField.setWidth("100%");
		return textField;
	}

	private TextField getCaregiving() {
		TextField textField = new TextField("Behandlungsart");
		textField.setValue(model.getMedicalAppointment().getCareGiving().getName());
		textField.setEnabled(false);
		textField.setWidth("100%");
		return textField;
	}

	private TextField getMedicalAppointmentDescription() {
		TextField textField = new TextField("Grund des Besuches");
		textField.setValue(model.getMedicalAppointment().getDescription());
		textField.setEnabled(false);
		textField.setWidth("100%");
		return textField;
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

	public void setController(ConfirmMedicalAppointmentController aController) {
		controller = aController;
	}

	public ConfirmMedicalAppointmentModel getModel() {
		return model;
	}

	public void setModel(ConfirmMedicalAppointmentModel aModel) {
		model = aModel;
	}
}