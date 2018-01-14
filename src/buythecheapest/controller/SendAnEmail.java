package buythecheapest.controller;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

public class SendAnEmail {
	private static final String HOST = "smtp.gmail.com";
	private static final int PORT = 465;
	private static final String SUBJECT = "New announcement!";
	private static String from;
	private static String password;
	private static String to;
	private String content;

	public void setFrom(String from) {
		this.from = from;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public SendAnEmail(String fromAndTo, String password) {
		setFrom(fromAndTo);
		setTo(fromAndTo);
		setPassword(password);
	}

	public void send() throws MessagingException {
		
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.auth", "true");
		//session initialization
		Session mailSession = Session.getDefaultInstance(props);
		//Debug setting if you don't want to see log, delete this line;
		mailSession.setDebug(true);
		//create a message
		MimeMessage message = new MimeMessage(mailSession);
		message.setSubject(SUBJECT);
		message.setContent(content, "text/plain; charset=ISO-8859-2");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		Transport transport = mailSession.getTransport();
		transport.connect(HOST, PORT, from, password);
		//send a message
		transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		transport.close();
	}

	public void test() {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.auth", "true");

		Session mailSession = Session.getDefaultInstance(props);

		mailSession.setDebug(true);

		MimeMessage message = new MimeMessage(mailSession);
		try {
			message.setSubject(SUBJECT);
			message.setContent(content, "text/plain; charset=ISO-8859-2");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport transport = mailSession.getTransport();
			transport.connect(HOST, PORT, from, password);
		} catch (MessagingException e) {
			JOptionPane.showMessageDialog(null, "Incorrect address or password");

		}

	}

}