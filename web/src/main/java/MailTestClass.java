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

public class MailTestClass {

    public void sendMailClass () throws MessagingException, IOException {

        final Properties properties = new Properties();
        properties.load(MailTestClass.class.getClassLoader().getResourceAsStream("mail.properties"));

        User user = new User();

        user.setMail("Lino220037@gmail.com");

        String userMail = user.getMail();
        Session mailSession = Session.getDefaultInstance(properties);

        String generationLink = mailGenerationLink();
        user.setToken(generationLink);
        MimeMessage message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress("3592401@gmail.com"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(userMail));
        message.setSubject("Hello");
        message.setText("http://localhost:8080/user-successful/" + generationLink);

        Transport transport = mailSession.getTransport();
        transport.connect(null, "andrewevlash");
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("Mail Send!");
        transport.close();
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

    public static void main(String[] args) throws IOException, MessagingException {
        MailTestClass mailTestClass = new MailTestClass();
        mailTestClass.sendMailClass();
    }
}
