package de.th_koeln.doctorcalendar.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.vaadin.spring.annotation.SpringComponent;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;
import de.th_koeln.doctorcalendar.application.entity.User;

@SpringComponent
public class MailSender {

	private org.springframework.mail.MailSender mailSender = new JavaMailSenderImpl();

	public void sendReverseMail(MedicalAppointment aMedicalAppointment, String aReverseReason) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(getMailAdress(aMedicalAppointment.getUser()));
		mail.setSubject("Ärztekalender: Dein Termin wurde vom Arzt gecancelt");
		mail.setText("Hallo " + aMedicalAppointment.getUser().getFullname() + " dein Termin vom " + aMedicalAppointment.getDate() + " um "
				+ aMedicalAppointment.getFormattedTime() + "beim Arzt " + aMedicalAppointment.getMedicalOffice() + " wurde mit folgendem Grund abgesagt: "
				+ aReverseReason);
		send(mail);
	}

	public void sendAcceptedMail(MedicalAppointment aMedicalAppointment) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(getMailAdress(aMedicalAppointment.getUser()));
		mail.setSubject("Ärztekalender: Dein Termin wurde vom Arzt bestätigt");
		mail.setText("Hallo " + aMedicalAppointment.getUser().getFullname() + " dein Termin vom " + aMedicalAppointment.getDate() + " um "
				+ aMedicalAppointment.getFormattedTime() + "beim Arzt " + aMedicalAppointment.getMedicalOffice() + " wurde bestätigt");
		send(mail);
	}

	private void send(SimpleMailMessage aMail) {
		//mocking for prototyp, because local no mail server is available
		System.out.println(aMail.toString());
		//		mailSender.send(aMail);
	}

	private String getMailAdress(User aUser) {
		return aUser.getEmail();
	}

}
