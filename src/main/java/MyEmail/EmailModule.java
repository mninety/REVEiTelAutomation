package MyEmail;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
 
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import MyAction.ActionModule;
import MyVariable.ProjectDeliveryAutoEmailer;
import MyVariable.VariableModule;

public class EmailModule {
	static Logger logEmailModule = Logger.getLogger(EmailModule.class.getName());
	//VariableModule variabledo = new VariableModule();
	
    public static void sendEmailWithAttachments(String toAddress,
            String subject, String message, String EmailType)
            throws AddressException, MessagingException {
        // sets SMTP server properties
    	//System.out.println("mailhost: "+VariableModule.mailhost);
    	String fromuser="";
    	String fromuserpass="";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", VariableModule.mailhost);
        properties.put("mail.smtp.port", VariableModule.mailport);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        fromuser=VariableModule.mailFrom;
        fromuserpass=VariableModule.mailpassword;
        
        properties.put("mail.user", fromuser);
        properties.put("mail.password", fromuserpass);
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(VariableModule.mailFrom, VariableModule.mailpassword);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        try {
			msg.setFrom(new InternetAddress(fromuser, "REVE SQA"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        //InternetAddress fromAddress=new InternetAddress(fromuser, "REVE SQA");
        InternetAddress[] iAdressArray = InternetAddress.parse(toAddress);
        msg.setRecipients(Message.RecipientType.TO, iAdressArray);
        
        if(!EmailType.equals("5"))
        {
	        InternetAddress[] ccAddresses = { new InternetAddress(VariableModule.mailToC) };
	        msg.setRecipients(Message.RecipientType.CC, ccAddresses);
        }
        
        InternetAddress[] bccAddresses = { new InternetAddress(VariableModule.mailToB) };
        msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
        
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
/*        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                    attachPart.attachFile(filePath);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }*/
 
        try {
			// sets the multi-part as e-mail's content
			msg.setContent(multipart);
 
			// sends the e-mail
			Transport.send(msg);
			
			logEmailModule.debug("Mail has sent successfully for "+EmailType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
}
