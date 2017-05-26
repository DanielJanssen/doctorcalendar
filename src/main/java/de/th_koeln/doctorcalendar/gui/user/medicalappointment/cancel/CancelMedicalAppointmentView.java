package de.th_koeln.doctorcalendar.gui.user.medicalappointment.cancel;

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
public class CancelMedicalAppointmentView extends Window {

	private static final long serialVersionUID = 1L;
	private CancelMedicalAppointmentController controller;
	private CancelMedicalAppointmentModel model;

	public CancelMedicalAppointmentView() {

		super("Termin löschen?");
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
		content.addComponent(getButtons());
		content.setMargin(true);

		setContent(content);
	}

	private Label getHeaderCaption() {
		return new Label("Möchten Sie den folgenden Termin wirklich löschen?");
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

	public void setController(CancelMedicalAppointmentController aController) {
		controller = aController;
	}

	public CancelMedicalAppointmentModel getModel() {
		return model;
	}

	public void setModel(CancelMedicalAppointmentModel aModel) {
		model = aModel;
	}

}
