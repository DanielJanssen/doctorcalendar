package de.th_koeln.doctorcalendar.gui.user;

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
import de.th_koeln.doctorcalendar.gui.user.cancelmedicalappointment.CancelMedicalAppointmentController;
import de.th_koeln.doctorcalendar.service.medicalappointment.MedicalAppointmentService;

@SpringComponent
@VaadinSessionScope
public class NextMedicalAppointmentController {

	@Autowired
	private MedicalAppointmentService medicalAppointmentService;
	@Autowired
	private NextMedicalAppointmentView view;
	CancelMedicalAppointmentController cancelMedicalAppointmentController;

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

	public ClickListener getClickListener() {
		return new ClickListener() {

			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				// TODO rt57, 04.05.2017: gucke ob was selektiert, wenn ja dann popup-dialog
				if (getModel().getSelectedMedicalAppointment() == null) {
					Notification.show("Bitte wähle zuerst einen Termin aus");
					return;
				}
				new CancelMedicalAppointmentController().initView(getModel().getSelectedMedicalAppointment());
			}
		};
	}

	public NextMedicalAppointmentModel getModel() {
		return view.getModel();
	}

}
