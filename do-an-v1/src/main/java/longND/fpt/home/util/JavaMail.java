package longND.fpt.home.util;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
public class JavaMail {
	private final String MAIL = "longndhe140970@fpt.edu.vn";
	private final String PASSWORD = "vzhwjtdmjirpknci";

	@Autowired
	private JavaMailSender emailSender;

//	public void sentEmail(String toEmail, String subject, String text) {
//
//		// Config
//		Properties props = new Properties();
//		props.put("mail.port", "587");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.host", "smtp.gmail.com");
//
//		// Authenticator
//		Session session = Session.getInstance(props, new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				return new PasswordAuthentication(MAIL, PASSWORD);
//			}
//		});
//
//		// Mail info
//		try {
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress(MAIL));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//			message.setSubject(subject);
//			message.setText(text);
//
//			Transport.send(message);
//			System.out.println("Message sent successfully...");
//
//		} catch (MessagingException e) {
//			throw new RuntimeException(e);
//		}
//	}

	public void sentEmail(String toEmail, String subject, String text) {

		// Mail info
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setSubject(subject);
			message.setFrom(MAIL);
			message.setTo(toEmail);
			message.setText(text);

			emailSender.send(message);
			System.out.println("Message sent successfully...");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
}
