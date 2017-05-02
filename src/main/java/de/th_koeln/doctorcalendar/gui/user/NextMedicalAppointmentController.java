package de.th_koeln.doctorcalendar.gui.user;

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
public class NextMedicalAppointmentController {

	@Autowired
	private MedicalAppointmentService medicalAppointmentService;
	@Autowired
	private NextMedicalAppointmentView view;

	public void findNextMedicalAppointments() {
		List<MedicalAppointment> tempNextMedicalAppointments = medicalAppointmentService.getNextMedicalAppointments(view.getUserName());
		view.setModel(new NextMedicalAppointmentModel(tempNextMedicalAppointments));
	}

	public NextMedicalAppointmentView getView() {
		return view;
	}

	@PostConstruct
	public void init() {
		view.setController(this);
	}

	public SelectionListener getSelectionListener() {
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

	public NextMedicalAppointmentModel getModel() {
		return view.getModel();
	}

}
