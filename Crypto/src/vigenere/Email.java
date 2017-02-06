package vigenere;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

public class Email extends JFrame{
    public static JLabel senderEmail, password, message, receiverEmail;
    public static JTextField emailTextField, passwordTextField, receiverEmailTextField;
    public static Button send;
    emailSender sendEmail;
    Container container;
    GridBagConstraints object;
    public static String messageContent;
    
    public Email(){
        container = getContentPane();
        container.setBackground(Color.LIGHT_GRAY);
        container.setLayout(new GridBagLayout());
        object = new GridBagConstraints();
        object.insets = new Insets(5,5,0,0);
        
        senderEmail = new JLabel("From");
        object.gridx = 1;
        object.gridy = 1;
        container.add(senderEmail, object);
        
        receiverEmail = new JLabel("To");
        object.gridx = 1;
        object.gridy = 3;
        container.add(receiverEmail, object);
        
        password = new JLabel("PassWord");
        object.gridx = 1;
        object.gridy = 2;
        container.add(password, object);
        
        emailTextField = new JTextField(20);
        object.gridx = 2;
        object.gridy = 1;
        container.add(emailTextField, object);
        
        receiverEmailTextField = new JTextField(20);
        object.gridx = 2;
        object.gridy = 3;
        container.add(receiverEmailTextField, object);
        
        passwordTextField = new JPasswordField(20);
        object.gridx = 2;
        object.gridy = 2;
        container.add(passwordTextField, object);
        
        
        send = new Button("Send");
        object.gridx = 2;
        object.gridy = 5;
        container.add(send, object);
        sendEmail = new emailSender();
        send.addActionListener(sendEmail);
        
        setTitle("Send Email");
        setSize(600,300);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setResizable(true);
    }
    
    public class emailSender implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String userEmail = emailTextField.getText();
            String receiverEmail = receiverEmailTextField.getText();
            String password = passwordTextField.getText();
            String message = messageContent;
            sendEmail(userEmail, receiverEmail, password, message);
        }
        
    }
    public static void sendEmail(String senderEmail, String recipientEmail, String password, String encryptedMessage){
        Properties props = System.getProperties();
        
        //sets the properties of the email to be sent
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.fallback", "false");
        
        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(true);
        
        Message mailMessage = new MimeMessage(mailSession);
        
        try {
            mailMessage.setFrom(new InternetAddress(senderEmail));
            mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            mailMessage.setContent(encryptedMessage, "text/html");
            
            Transport transport = mailSession.getTransport("smtp");
            transport.connect("smtp.gmail.com", senderEmail, password);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            
            JOptionPane.showMessageDialog(null, "MESSAGE SENT","MESSAGE SENT",JOptionPane.OK_OPTION);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
