package de.th_koeln.doctorcalendar.service.email;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.vaadin.spring.annotation.SpringComponent;

import de.th_koeln.doctorcalendar.application.entity.MedicalAppointment;

@SpringComponent
public class MailSender {

	private org.springframework.mail.MailSender mailSender = new JavaMailSenderImpl();

	public void sendReverseMail(MedicalAppointment aMedicalAppointment) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo("janssen_daniel@web.de");
		mail.setSubject("Ärztekalender: Dein Termin wurde vom Arzt gecancelt");
		mail.setText("Test");
		send(mail);
	}

	private void send(SimpleMailMessage aMail) {
		try {
			mailSender.send(aMail);
		} catch (MailException ex) {
			// simply log it and go on...
			System.err.println(ex.getMessage());
		}
	}

}
