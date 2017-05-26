package de.th_koeln.doctorcalendar.gui.user.medicalappointment.past;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.service.medicalappointment.MedicalAppointmentService;

@SpringComponent
@VaadinSessionScope
public class PastMedicalAppointmentController {

	@Autowired
	MedicalAppointmentService service;

	@Autowired
	PastMedicalAppointmentView view;

	public PastMedicalAppointmentView getView() {
		return view;
	}

	@PostConstruct
	public void init() {
		view.setController(this);
	}

	public SelectionListener getGridSelectionListener() {
		return new SelectionListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void select(SelectionEvent aEvent) {
				if (aEvent.getSelected() != null && !aEvent.getSelected().isEmpty()) {
					getModel().setSelectedMedicalAppointment((MedicalAppointment) aEvent.getSelected().iterator().next());
				} else {
					getModel().setSelectedMedicalAppointment(null);
				}
			}
		};
	}

	public PastMedicalAppointmentModel getModel() {
		return view.getModel();
	}

	public void findPastMedicalAppointments() {
		List<MedicalAppointment> medicalAppointments = service.getPastMedicalAppointment(view.getUserName());
		view.setModel(new PastMedicalAppointmentModel(medicalAppointments));
	}

}
