package mail;

import frames.SessionDialog;
import java.io.File;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author yvan
 */
public final class Mail {

    private final SessionDialog sd = new SessionDialog();
    private final Session session;

    public Mail() {
        sd.setVisible(true);
        session = sd.getSession();
    }

    public void sendMessage(String subject, String to, File file) {
        new Thread() {

            @Override
            public void run() {

                try {
                    FileDataSource attach = new FileDataSource(file);
                    DataHandler handler1 = new DataHandler(attach);
                    MimeBodyPart ftos = new MimeBodyPart();

                    ftos.setDataHandler(handler1);
                    ftos.setFileName(attach.getName());

                    MimeMultipart mimeMultipart = new MimeMultipart();

                    mimeMultipart.addBodyPart(ftos);

                    Message message = new MimeMessage(session);

                    message.setFrom(new InternetAddress(sd.getUserName()));
                    message.setReplyTo(new Address[]{new InternetAddress(sd.getUserName())});
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
                    message.setSubject(subject);
                    message.setContent(mimeMultipart);
                    
                    

                    Transport.send(message);

                } catch (AuthenticationFailedException e) {
                    e.printStackTrace();
                    try {
                        System.err.println(session.getTransport().getURLName());
                        System.err.println(session.getPasswordAuthentication(session.getTransport().getURLName()));
                    } catch (NoSuchProviderException ex) {
                        Logger.getLogger(Mail.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();
    }

}
