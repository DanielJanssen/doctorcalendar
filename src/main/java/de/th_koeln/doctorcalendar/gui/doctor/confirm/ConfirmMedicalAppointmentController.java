package de.th_koeln.doctorcalendar.gui.doctor.confirm;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.service.email.MailSender;
import de.th_koeln.doctorcalendar.service.medicalappointment.MedicalAppointmentService;

@SpringComponent
@VaadinSessionScope
public class ConfirmMedicalAppointmentController {

	ConfirmMedicalAppointmentView view;

	@Autowired
	MedicalAppointmentService service;

	@Autowired
	MailSender mailSender;

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
				mailSender.sendAcceptedMail(view.getModel().getMedicalAppointment());
				service.setMedicalAppointmentToAccepted(view.getModel().getMedicalAppointment());
				view.close(); // Close the sub-window
			}
		};
	}

	public void initView(MedicalAppointment aMedicalAppointment) {
		if (view == null) {
			view = new ConfirmMedicalAppointmentView();
			view.setController(this);
			view.setModel(new ConfirmMedicalAppointmentModel(aMedicalAppointment));
			view.enter();
		}

		UI.getCurrent().addWindow(view);
	}
}
