package de.th_koeln.doctorcalendar.gui.user.cancelmedicalappointment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.service.medicalappointment.MedicalAppointmentService;

@SpringComponent
@VaadinSessionScope
public class CancelMedicalAppointmentController {

	@Autowired
	CancelMedicalAppointmentView view;

	@Autowired
	MedicalAppointmentService service;

	@PostConstruct
	public void init() {
		view.setController(this);
		view.enter();
	}

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
				service.setMedicalAppointmentToFree(view.getModel().getMedicalAppointment());
				view.close(); // Close the sub-window
			}
		};
	}

	public void initView(MedicalAppointment aMedicalAppointment) {
		view.setModel(new CancelMedicalAppointmentModel(aMedicalAppointment));
		UI.getCurrent().addWindow(view);
	}
}
