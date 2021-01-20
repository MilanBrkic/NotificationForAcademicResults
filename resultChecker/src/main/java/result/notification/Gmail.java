package result.notification;

import domain.MyMessage;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Gmail {

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

    public void sendMail(MyMessage msg) throws Exception {
        String recepient = msg.getReceiver();
        System.out.println("Preparing to send to " + recepient + "...");
        String myEmail = "milan.brkic1998@gmail.com";
        String password = msg.getPassword();

        Session session = pripremaZaSlanje(myEmail, password);
        Message message = prepareMessage(session, myEmail, msg);

        Transport.send(message);
        System.out.println("Message sent successfully to " + recepient);

    }

    private static Message prepareMessage(Session session, String myEmail, MyMessage msg) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(msg.getReceiver()));
            message.setSubject(msg.getSubject());
            message.setText(msg.getMessage());

            return message;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

}
