
package com.email;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
    
    final String username = "bodeanely94@gmail.com";
    final String password = "Parolamea1994*.";
    private String to;
    private String token;
    
    static Properties props = new Properties();
    static
   {
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");
   }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public EmailSender(String to, String token){
        this.to = to;
        this.token = token;
    }
    
    public void sendMail() {

        String emailContent = null;
        if(token != null){
        emailContent = EmailTemplate.templateGenerator(token);
        }
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("bodeanely94@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("bodeanely94@gmail.com"));
			message.setSubject("Your token");
			message.setContent(emailContent, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
    
    public static void main(String args[]){
        EmailSender em = new EmailSender("bodeanely94@gmail.com","1234567");
        em.sendMail();
    }
}
