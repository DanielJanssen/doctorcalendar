package de.th_koeln.doctorcalendar.gui.user.medicalappointment.next;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.SelectionEvent;
import com.vaadin.event.SelectionEvent.SelectionListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.gui.user.medicalappointment.cancel.CancelMedicalAppointmentController;
import de.th_koeln.doctorcalendar.service.medicalappointment.MedicalAppointmentService;

@SpringComponent
@VaadinSessionScope
public class NextMedicalAppointmentController {

	@Autowired
	private MedicalAppointmentService medicalAppointmentService;
	@Autowired
	private NextMedicalAppointmentView view;

	@Autowired
	CancelMedicalAppointmentController cancelMedicalAppointmentController;

	public void findNextMedicalAppointments() {
		List<MedicalAppointment> tempNextMedicalAppointments = medicalAppointmentService.getNextMedicalAppointments(getView().getUserName());
		getView().setModel(new NextMedicalAppointmentModel(tempNextMedicalAppointments));
	}

	@PostConstruct
	public void init() {
		getView().setController(this);
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

	public ClickListener getCancelMedicalAppointmentClickListener() {
		return new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				if (getModel().getSelectedMedicalAppointment() == null) {
					Notification.show("Bitte wähle zuerst einen Termin aus");
					return;
				}
				cancelMedicalAppointmentController.initView(getModel().getSelectedMedicalAppointment());
			}
		};
	}

	public NextMedicalAppointmentView getView() {
		return view;
	}

	public NextMedicalAppointmentModel getModel() {
		return getView().getModel();
	}

}
