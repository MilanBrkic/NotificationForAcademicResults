package result.notification;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Gmail extends Thread{
	static String recepient;
	public Gmail(String recepient) {
		this.recepient = recepient;
	}
	
	@Override
	public void run() {
		try {
			sendMail(recepient);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Session pripremaZaSlanje(final String myEmail, final String password) {
		Properties properties = new Properties();
		
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		
		
		Session session = Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myEmail, password);
			}
		
		});
		return session;
	}
	
	public static void sendMail(String recepient) throws Exception {
		System.out.println("Preparing to send to "+recepient+"...");
		String myEmail = "milan.brkic1998@gmail.com";
		String password = "bsdtoggmszbxswfz";
		
		Session session = pripremaZaSlanje(myEmail,password);
		Message message = prepareMessage(session, myEmail, recepient);
		
		Transport.send(message);
		System.out.println("Message sent successfully to "+recepient);
		
	}

	private static Message prepareMessage(Session session, String myEmail, String recepient) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Nova objava");
			message.setText("Proveri is.fon.bg.ac.rs mozda su izasli rezultati");
			
			
			return message;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
}
