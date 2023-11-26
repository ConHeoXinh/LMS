package longND.fpt.home.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class JavaMail {
	private final static String MAIL = "longndhe140970@fpt.edu.vn";
	private final static String PASSWORD = "vzhwjtdmjirpknci";

	@Autowired
	private JavaMailSender emailSender;

	public static void sendVerifyEmail(String to, String subject, String body) throws EmailException {
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(MAIL, PASSWORD));
		email.setStartTLSEnabled(true);
		email.setFrom(MAIL);
		email.addTo(to);
		email.setSubject(subject);
		email.setHtmlMsg(body);
		email.send();
	}

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
