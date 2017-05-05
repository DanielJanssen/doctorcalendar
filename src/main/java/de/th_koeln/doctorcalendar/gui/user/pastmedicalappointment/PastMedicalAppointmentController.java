package de.th_koeln.doctorcalendar.gui.user.pastmedicalappointment;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

@SpringComponent
@VaadinSessionScope
public class PastMedicalAppointmentController {

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
		List<MedicalAppointment> medicalAppointments = new ArrayList<>();
		// TODO rt57, 05.05.2017: service findData
		view.setModel(new PastMedicalAppointmentModel(medicalAppointments));
	}

}
