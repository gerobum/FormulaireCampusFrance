package frames;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Properties;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author yvan
 */
public class SessionDialog extends JDialog implements ActionListener {

    private final JButton ok = new JButton("Ok");
    private final JButton cancel = new JButton("Cancel");
    private final JTextField jtfUserName = new JTextField(50);
    private final JPasswordField jtfPassword = new JPasswordField(50);
    private Properties properties;
    private String userName = "";

    private Session session;

    public SessionDialog() {
        initUI();
        initSession();
        setModalityType(ModalityType.APPLICATION_MODAL);
        
    }

    public Session getSession() {
        return session;
    }

    public String getUserName() {
        return userName;
    }

    
    private void initUI() {
        setLayout(new BorderLayout());
        JPanel centre = new JPanel(new GridLayout(2, 1));
        JPanel sud = new JPanel();
        JPanel jpUsername = new JPanel();
        JPanel jpPassword = new JPanel();
        jpUsername.add(new JLabel("adresse mail : ", JLabel.RIGHT));
        jpUsername.add(jtfUserName);
        jpPassword.add(new JLabel("mot de passe : ", JLabel.RIGHT));
        jpPassword.add(jtfPassword);
        centre.add(jpUsername);
        centre.add(jpPassword);
        sud.add(ok);
        sud.add(cancel);
        add(centre, "Center");
        add(sud, "South");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
        
        ok.addActionListener(this);
        cancel.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ok == e.getSource()) {
            userName = jtfUserName.getText();
            session = Session.getInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(userName, Arrays.toString(jtfPassword.getPassword()));
                }
            });
            /*jtfUserName.setText("");
            jtfPassword.setText("");*/
            System.out.println("OK");
            System.out.println(session);
        }
        setVisible(false);
    }

    private void initSession() {
        // 1 -> Création de la session
        properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");

        session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("", "");
            }
        });
    }
}
