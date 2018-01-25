package mail;

import entity.User;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

public class MailSender {
    private final String AUTO_MAIL_LOGIN = "3592401@gmail.com";
    private final String AUTO_MAIL_PASSWORD = "andrewevlash";
    private final String MAIL_PROPERTIES = "mail.properties";


    public User mailConfirmation(User user) throws IOException, MessagingException {
        final Properties properties = new Properties();
        properties.load(MailSender.class.getClassLoader().getResourceAsStream("mail.properties"));

        String userMail = user.getMail();
        Session mailSession = Session.getDefaultInstance(properties);

        MailSender mailSender = new MailSender();
        String generationLink = mailSender.mailGenerationLink();
        user.setToken(generationLink);
        MimeMessage message = new MimeMessage(mailSession);
        Test test = new Test();
//        andrewevl298@gmail.com
        message.setFrom(new InternetAddress("3592401@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
        message.setSubject("Hello");
        message.setText("http://localhost:8080/user-successful/" + generationLink);

        Transport transport = mailSession.getTransport();
        transport.connect(null, "andrewevlash");
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
        return user;
    }

    private String mailGenerationLink() {
        int length = 12;
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = characters.charAt(random.nextInt(characters.length()));
        }
        return new String(text);
    }
}
