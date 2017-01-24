
package p;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import p.EmailTemplate;

public class EmailSender {

    final String username = "tokengeneratorj@gmail.com";
    final String password = "Parola2017*";
    private String email;
    private String token;

    static Properties props = new Properties();
    static
   {
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587");
   }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EmailSender(String email,String token){
        this.email = email;
        this.token = token;
        this.sendMail();

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
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("Your token");
			message.setContent(emailContent, "text/html; charset=utf-8");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

    // public static void main(String args[]){
    //     EmailSender em = new EmailSender("bodeanely94@gmail.com","1234567");
    //     em.sendMail();
    // }
}
