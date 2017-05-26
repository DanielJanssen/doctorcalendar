package de.th_koeln.doctorcalendar.gui.user.medicalappointment.reserve;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.service.medicalappointment.MedicalAppointmentService;

@SpringComponent
@VaadinSessionScope
public class ReserveMedicalAppointmentController {

	ReserveMedicalAppointmentView view;

	@Autowired
	MedicalAppointmentService service;

	protected ClickListener getCancelCloseListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				view.close(); // Close the sub-window
			}
		};
	}

	protected ClickListener getOkCloseListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(@SuppressWarnings("unused") ClickEvent event) {
				view.setUserInput();
				if (view.getModel().getMedicalAppointment().getCareGiving() == null) {
					Notification.show("Bitte wähle eine Behandlung aus.");
					return;
				}
				service.reserveMedicalAppointment(view.getModel().getMedicalAppointment(), view.getUserName());
				view.close(); // Close the sub-window
			}
		};
	}

	public void initView(MedicalAppointment aMedicalAppointment) {
		if (view == null) {
			view = new ReserveMedicalAppointmentView();
			view.setController(this);
			view.setModel(new ReserveMedicalAppointmentModel(aMedicalAppointment, aMedicalAppointment.getMedicalOffice().getCaregivings()));
			view.enter();
		}

		UI.getCurrent().addWindow(view);
	}
}
