package de.th_koeln.doctorcalendar.service.testdata;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.spring.annotation.SpringComponent;

import de.th_koeln.doctorcalendar.application.entity.Address;
import de.th_koeln.doctorcalendar.application.entity.Caregiving;
import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.application.entity.MedicalOffice;
import de.th_koeln.doctorcalendar.application.entity.PhoneNumber;
import de.th_koeln.doctorcalendar.application.entity.User;
import de.th_koeln.doctorcalendar.application.entity.enums.MedicalAppointmentState;
import de.th_koeln.doctorcalendar.application.entity.enums.Speciality;
import de.th_koeln.doctorcalendar.persistence.repository.MedicalAppointmentRepository;
import de.th_koeln.doctorcalendar.persistence.repository.MedicalOfficeRepository;
import de.th_koeln.doctorcalendar.persistence.repository.UserRepository;

@SpringComponent
public class TestdataGenerator {

	@Autowired
	UserRepository userRepository;

	@Autowired
	MedicalOfficeRepository medicalOfficeRepository;

	@Autowired
	MedicalAppointmentRepository medicalAppointmentRepository;

	MedicalOffice firstMedicalOffice;
	MedicalOffice secondMedicalOffice;
	MedicalOffice thirdMedicalOffice;

	public void generateTestData() {
		firstMedicalOffice = medicalOfficeRepository.save(getFirstMedicalOffice());
		secondMedicalOffice = medicalOfficeRepository.save(getSecondMedicalOffice());
		thirdMedicalOffice = medicalOfficeRepository.save(getThirdMedicalOffice());

		userRepository.save(getFirstUser());
		userRepository.save(getSecondUser());

		medicalAppointmentRepository.save(getFreeMedicalAppointments());
		//freie Termine
	}

	private MedicalOffice getFirstMedicalOffice() {
		MedicalOffice medicalOffice = new MedicalOffice("Arztpraxis Dr. Günther", Speciality.INTERNIST, new Date(), new Date(), "www.guenther-allgemein.de",
				"info@guenther-allgemein.de", new PhoneNumber("0211", "1234567"), new Address("Koenigsallee", "20", 40223, "Düsseldorf"));
		medicalOffice.getCaregivings().add(new Caregiving("Blutabnahme", 15));
		medicalOffice.getCaregivings().add(new Caregiving("EKG", 30));
		medicalOffice.getCaregivings().add(new Caregiving("Sprechstunde", 15));
		medicalOffice.getEmployees().add(new User("SusMue", "Susanne", "Müller", "mueller@guenther-allgemein.de", "SusMue", medicalOffice));
		return medicalOffice;
	}

	private MedicalOffice getSecondMedicalOffice() {
		MedicalOffice medicalOffice = new MedicalOffice("Zahnarztpraxis Stockum", Speciality.DENTIST, new Date(), new Date(), "www.zahnarzt-stockum.de",
				"info@zahnarzt-stockum.de", new PhoneNumber("0211", "223344"), new Address("Lugallee", "12", 40350, "Düsseldorf"));
		medicalOffice.getCaregivings().add(new Caregiving("Kontrolle", 15));
		medicalOffice.getCaregivings().add(new Caregiving("Zahn ziehen", 60));
		medicalOffice.getCaregivings().add(new Caregiving("Röntgen", 15));
		medicalOffice.getEmployees().add(new User("Sabbi", "Sabine", "Baum", "sabinebaum@zahnarzt-stockum.de", "Sabbi", medicalOffice));
		return medicalOffice;
	}

	private MedicalOffice getThirdMedicalOffice() {
		MedicalOffice medicalOffice = new MedicalOffice("Zahnarztpraxis Benrath", Speciality.DENTIST, new Date(), new Date(), "www.zahnarzt-benrath.de",
				"info@zahnarzt-benrath.de", new PhoneNumber("0211", "7649104"), new Address("Am Schloss", "4a", 40250, "Düsseldorf"));
		medicalOffice.getCaregivings().add(new Caregiving("Kontrolle", 20));
		medicalOffice.getCaregivings().add(new Caregiving("Zahn ziehen", 80));
		medicalOffice.getCaregivings().add(new Caregiving("Röntgen", 20));
		medicalOffice.getEmployees().add(new User("PetraG", "Petra", "Goos", "petragoos@web.de", "PetraG", medicalOffice));
		return medicalOffice;
	}

	private User getFirstUser() {
		User user = new User("PeterMueller", "Peter", "Müller", "muellerp@web.de", "PeterMueller", new PhoneNumber("01520", "1726905"),
				new Address("Lindenstraße", "36", 40233, "Düsseldorf"));
		user.getMedicalAppointments().add(new MedicalAppointment(getDate(-5), getTimeAt(9, 0), getTimeAt(9, 20), "Kontrolle", null, user, thirdMedicalOffice,
				MedicalAppointmentState.ACCEPTED, thirdMedicalOffice.getCaregivings().get(0)));
		user.getMedicalAppointments().add(new MedicalAppointment(getDate(5), getTimeAt(9, 0), getTimeAt(9, 15), "Blutabnahme", null, user, firstMedicalOffice,
				MedicalAppointmentState.RESERVED, firstMedicalOffice.getCaregivings().get(0)));
		user.getMedicalAppointments().add(new MedicalAppointment(new Date(), getTimeAt(9, 0), getTimeAt(10, 0), "Zahn ziehen", null, user, thirdMedicalOffice,
				MedicalAppointmentState.ACCEPTED, thirdMedicalOffice.getCaregivings().get(1)));
		user.getMedicalAppointments().add(new MedicalAppointment(getDate(-20), getTimeAt(15, 0), getTimeAt(15, 30), "EKG", null, user, firstMedicalOffice,
				MedicalAppointmentState.ACCEPTED, firstMedicalOffice.getCaregivings().get(1)));
		return user;
	}

	private List<MedicalAppointment> getFreeMedicalAppointments() {
		List<MedicalAppointment> freeMedicalAppointments = new ArrayList<>();
		freeMedicalAppointments.add(new MedicalAppointment(getDate(2), getTimeAt(12, 0), getTimeAt(12, 15), MedicalAppointmentState.FREE, firstMedicalOffice));
		freeMedicalAppointments.add(new MedicalAppointment(getDate(2), getTimeAt(12, 15), getTimeAt(12, 30), MedicalAppointmentState.FREE, firstMedicalOffice));
		freeMedicalAppointments.add(new MedicalAppointment(getDate(2), getTimeAt(14, 30), getTimeAt(14, 45), MedicalAppointmentState.FREE, firstMedicalOffice));
		freeMedicalAppointments.add(new MedicalAppointment(getDate(2), getTimeAt(14, 15), getTimeAt(14, 30), MedicalAppointmentState.FREE, firstMedicalOffice));
		freeMedicalAppointments.add(new MedicalAppointment(getDate(2), getTimeAt(12, 0), getTimeAt(12, 15), MedicalAppointmentState.FREE, secondMedicalOffice));
		freeMedicalAppointments
				.add(new MedicalAppointment(getDate(2), getTimeAt(12, 15), getTimeAt(12, 30), MedicalAppointmentState.FREE, secondMedicalOffice));
		freeMedicalAppointments
				.add(new MedicalAppointment(getDate(2), getTimeAt(14, 30), getTimeAt(14, 45), MedicalAppointmentState.FREE, secondMedicalOffice));
		freeMedicalAppointments
				.add(new MedicalAppointment(getDate(2), getTimeAt(14, 15), getTimeAt(14, 30), MedicalAppointmentState.FREE, secondMedicalOffice));
		freeMedicalAppointments.add(new MedicalAppointment(getDate(3), getTimeAt(8, 0), getTimeAt(8, 20), MedicalAppointmentState.FREE, thirdMedicalOffice));
		freeMedicalAppointments.add(new MedicalAppointment(getDate(3), getTimeAt(8, 20), getTimeAt(8, 40), MedicalAppointmentState.FREE, thirdMedicalOffice));
		freeMedicalAppointments.add(new MedicalAppointment(getDate(3), getTimeAt(8, 40), getTimeAt(9, 0), MedicalAppointmentState.FREE, thirdMedicalOffice));
		freeMedicalAppointments.add(new MedicalAppointment(getDate(3), getTimeAt(9, 0), getTimeAt(9, 20), MedicalAppointmentState.FREE, thirdMedicalOffice));
		return freeMedicalAppointments;
	}

	private User getSecondUser() {
		User user = new User("Susi", "Susanne", "Meier", "meier@gmail.com", "Susi", new PhoneNumber("01520", "1722405"),
				new Address("Poststraße", "46", 40233, "Düsseldorf"));
		user.setWorkingMedicalOffice(firstMedicalOffice);
		return user;
	}

	private Date getDate(Integer aDateChangeInDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, aDateChangeInDays);
		return calendar.getTime();
	}

	private Date getTimeAt(Integer aHour, Integer aMinute) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(0, 0, 0, aHour, aMinute);
		return calendar.getTime();
	}

}
