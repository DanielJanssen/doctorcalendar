package de.th_koeln.doctorcalendar.gui.doctor.find.cancel;

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
public class ReverseMedicalAppointmentController {

	ReverseMedicalAppointmentView view;

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
				if (view.getModel().getReasonOfReserve() == null || view.getModel().getReasonOfReserve() == "") {
					Notification.show("Bitte geben Sie einen Grund für die Absage für den Patienten an.");
					return;
				}
				service.setMedicalAppointmentToFree(view.getModel().getMedicalAppointment());
				// TODO rt57, 06.06.2017:  mail
				view.close(); // Close the sub-window
			}
		};
	}

	public void initView(MedicalAppointment aMedicalAppointment) {
		if (view == null) {
			view = new ReverseMedicalAppointmentView();
			view.setController(this);
			view.setModel(new ReverseMedicalAppointmentModel(aMedicalAppointment));
			view.enter();
		}

		UI.getCurrent().addWindow(view);
	}
}
