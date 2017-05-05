package de.th_koeln.doctorcalendar.gui.user.cancelmedicalappointment;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@SpringComponent
@VaadinSessionScope
public class CancelMedicalAppointmentView extends Window {

	private static final long serialVersionUID = 1L;
	private CancelMedicalAppointmentController controller;
	private CancelMedicalAppointmentModel model;

	public CancelMedicalAppointmentView() {

		super("Termin wirklich löschen?");
	}

	public void enter() {
		center();
		setModal(true);
		setClosable(false);

		VerticalLayout content = new VerticalLayout();
		content.addComponent(new Label("Just say it's OK!"));
		content.setMargin(true);
		setContent(content);

		HorizontalLayout buttonLayout = new HorizontalLayout();

		Button cancel = new Button("Abbrechen");
		cancel.addClickListener(controller.getCancelCloseListener());
		buttonLayout.addComponent(cancel);
		buttonLayout.setComponentAlignment(cancel, Alignment.BOTTOM_LEFT);

		Button ok = new Button("Ok");
		ok.addClickListener(controller.getOkCloseListener());
		buttonLayout.addComponent(ok);
		buttonLayout.setComponentAlignment(ok, Alignment.BOTTOM_RIGHT);

		content.addComponent(buttonLayout);
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
