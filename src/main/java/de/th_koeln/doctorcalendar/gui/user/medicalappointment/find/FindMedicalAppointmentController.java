package de.th_koeln.doctorcalendar.gui.user.medicalappointment.find;

import java.util.ArrayList;
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
import de.th_koeln.doctorcalendar.application.entity.User;
import de.th_koeln.doctorcalendar.gui.user.medicalappointment.reserve.ReserveMedicalAppointmentController;
import de.th_koeln.doctorcalendar.persistence.repository.UserRepository;
import de.th_koeln.doctorcalendar.service.medicalappointment.MedicalAppointmentService;

@SpringComponent
@VaadinSessionScope
public class FindMedicalAppointmentController {

	@Autowired
	FindMedicalAppointmentView view;

	@Autowired
	MedicalAppointmentService service;

	@Autowired
	ReserveMedicalAppointmentController reserveMedicalAppointmentController;

	@Autowired
	UserRepository userRepository;

	@PostConstruct
	public void init() {
		getView().setController(this);
		getView().setModel(new FindMedicalAppointmentModel(new FindMedicalAppointmentSearchParameter(), new ArrayList<>()));
	}

	public FindMedicalAppointmentView getView() {
		return view;
	}

	public ClickListener getResetClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				getModel().setSearchParameter(new FindMedicalAppointmentSearchParameter());
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

	public FindMedicalAppointmentModel getModel() {
		return getView().getModel();
	}

	public ClickListener getSearchClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				getView().setSearchParameterToModel();
				List<MedicalAppointment> foundMedicalAppointments = service.findMedicalAppointment(getModel().getSearchParameter());
				getModel().setMedicalAppointments(foundMedicalAppointments);
				getView().setItemsBeanItemContainer(foundMedicalAppointments);
			}
		};

	}

	public ClickListener getEmailClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				Notification.show("Diese Funktion wurde noch nicht implementiert.");
			}
		};
	}

	public ClickListener getDurationClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				if (getModel().getSelectedMedicalAppointment() == null) {
					Notification.show("Bitte wähle zuerst einen Arzt aus");
					return;
				}
				Notification.show("Diese Funktion wurde noch nicht implementiert.");
			}
		};

	}

	public ClickListener getMedicalOfficeDetailsClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				if (getModel().getSelectedMedicalAppointment() == null) {
					Notification.show("Bitte wähle zuerst einen Arzt aus");
					return;
				}
				Notification.show("Diese Funktion wurde noch nicht implementiert.");
			}
		};
	}

	public ClickListener getReserveClickListener() {
		return new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent aEvent) {
				if (getModel().getSelectedMedicalAppointment() == null) {
					Notification.show("Bitte wähle zuerst einen Termin aus");
					return;
				}
				reserveMedicalAppointmentController.initView(getModel().getSelectedMedicalAppointment());
			}
		};
	}

	public User getLoginUser() {
		return userRepository.findByLoginName(getView().getUserName());
	}
}
