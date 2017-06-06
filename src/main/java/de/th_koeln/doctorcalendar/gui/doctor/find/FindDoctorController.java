package de.th_koeln.doctorcalendar.gui.doctor.find;

import java.util.ArrayList;
import java.util.Date;
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
import de.th_koeln.doctorcalendar.gui.doctor.find.cancel.ReverseMedicalAppointmentController;
import de.th_koeln.doctorcalendar.service.medicalappointment.MedicalAppointmentService;

@SpringComponent
@VaadinSessionScope
public class FindDoctorController {

	@Autowired
	FindDoctorView view;

	@Autowired
	MedicalAppointmentService service;

	@Autowired
	ReverseMedicalAppointmentController reverseeMedicalAppointmentController;

	@PostConstruct
	public void init() {
		getView().setController(this);
		getView().setModel(new FindDoctorModel(new FindDoctorSearchParameter(), new ArrayList<>()));
	}

	public FindDoctorView getView() {
		return view;
	}

	public ClickListener getResetClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				getModel().setSearchParameter(new FindDoctorSearchParameter());
			}
		};
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

	public FindDoctorModel getModel() {
		return getView().getModel();
	}

	public ClickListener getSearchClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				getView().setSearchParameterToModel();
				List<MedicalAppointment> foundMedicalAppointments = service.findMedicalAppointment(getModel().getSearchParameter(), getView().getUserName());
				getModel().setMedicalAppointments(foundMedicalAppointments);
				getView().setItemsBeanItemContainer(foundMedicalAppointments);
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
				if (getModel().getSelectedMedicalAppointment().getUser() == null) {
					Notification.show("Der Termin muss bereits einem Patienten zugeordnet sein, um ihm absagen zu können");
					return;
				}
				if (getModel().getSelectedMedicalAppointment().getDate().before(new Date())) {
					Notification.show("Der Termin hat bereits stattgefunden und kann nicht mehr abgesagt werden");
					return;
				}
				reverseeMedicalAppointmentController.initView(getModel().getSelectedMedicalAppointment());
			}
		};
	}

	public ClickListener getOfferMedicalAppointmentsClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				Notification.show("Diese Funktion wurde noch nicht implementiert.");
			}
		};
	}

	public ClickListener getEditMedicalAppointmentClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				if (getModel().getSelectedMedicalAppointment() == null) {
					Notification.show("Bitte wähle zuerst einen Termin aus");
					return;
				}
				Notification.show("Diese Funktion wurde noch nicht implementiert.");
			}
		};
	}

	public ClickListener getPatientDetailsClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				if (getModel().getSelectedMedicalAppointment() == null) {
					Notification.show("Bitte wähle zuerst einen Patienten aus");
					return;
				}
				Notification.show("Diese Funktion wurde noch nicht implementiert.");
			}
		};
	}

	public ClickListener getConfirmMedicalAppointmentClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				if (getModel().getSelectedMedicalAppointment() == null) {
					Notification.show("Bitte wähle zuerst einen Termin aus");
					return;
				}
				Notification.show("Diese Funktion wurde noch nicht implementiert.");
			}
		};
	}

}
