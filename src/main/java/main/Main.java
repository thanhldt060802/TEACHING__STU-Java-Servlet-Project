package main;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {
	
	public static void sendEmail(String fromEmail, String password, String toEmail, String subject, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		Session session = Session.getInstance(props, authenticator);
		
		try {
			// Tạo message
			Message message = new MimeMessage(session);
			
			// Đặt người gửi
			message.setFrom(new InternetAddress(fromEmail));
			
			// Đặt người nhận
			message.setRecipient(RecipientType.TO, new InternetAddress(toEmail));
			
			// Đặt tiều đề
			message.setSubject(subject);
			
			// Đặt nội dung
			message.setText(body);
			
			// Gửi mail
			Transport.send(message);
			
			System.out.println("Send email successful");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		String fromEmail = "thanhldt060802@gmail.com";
		String password = "bofx jwoe ggob fxml";
		String toEmail = "thanhldt060802.sub@gmail.com";
		String subject = "Test gửi mail";
		String body = "Hello, world!";
		
		sendEmail(fromEmail, password, toEmail, subject, body);
		
	}

}
