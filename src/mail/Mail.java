package mail;

import java.io.File;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author yvan
 */
public class Mail {

    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    public static void sendMessage(String subject, String to, File file) {
        new Thread() {

            @Override
            public void run() {
                // 1 -> Création de la session
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.socketFactory.port", "465");
                properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "465");

                Session session = Session.getInstance(properties,
                        new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

                try {
                    FileDataSource attach = new FileDataSource(file);
                    DataHandler handler1 = new DataHandler(attach);
                    MimeBodyPart ftos = new MimeBodyPart();

                    ftos.setDataHandler(handler1);
                    ftos.setFileName(attach.getName());

                    MimeMultipart mimeMultipart = new MimeMultipart();

                    mimeMultipart.addBodyPart(ftos);

                    Message message = new MimeMessage(session);

                    message.setFrom(new InternetAddress(USERNAME));
                    message.setReplyTo(new Address[]{new InternetAddress(USERNAME)});
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    message.setSubject(subject);
                    message.setContent(mimeMultipart);

                    Transport.send(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

}
