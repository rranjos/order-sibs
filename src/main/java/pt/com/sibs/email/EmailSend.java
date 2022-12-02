package pt.com.sibs.email;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class EmailSend {

	@Autowired
	private JavaMailSender sender;

	@Value("${spring.mail.username}")
	private String from;

	private static final Logger logger = LogManager.getLogger(EmailSend.class);

	public void send(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(this.from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		sender.send(message);
		logger.info("[EMAIL : " + to + " SENT WITH SUCCESS!");
	}
}