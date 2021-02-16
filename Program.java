package test;


import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class Program  {
    public static void main(String[] args) throws MessagingException {
        String username = "myMail@mail.ru";
        String password = "myPassword123";

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.mail.ru"); //for gmail.com use port 465
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.mail.ru");

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("myMail@mail.ru"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("someMail@gmail.com")
            );
            message.setSubject("Тестовое письмо");
            message.setText("Проверка отправки письма из java приложения."
                    + "\n\n Проверка пройдена!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
