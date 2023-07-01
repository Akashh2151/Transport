package transportapk.servises;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class Emailservises {
    public boolean sendEmail(String subject,String message,String to) {
    	
    	boolean f=false;
    	
    	String from ="akashdesai2151@gmail.com";
    	
        String host = "smtp.gmail.com";
        
        Properties properties = System.getProperties();
        System.out.println("PROPERTITES"+properties);
        
		// Host set
		 
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        
 
        
        Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("akash.desai@aitglobalinc.com", "giptlgcvoeorkntk");// Put your email
																								// id and
																								// password here
			}
		});
		
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);
		
		try{
			
			m.setFrom(from);
			
			m.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
			
			m.setSubject(subject);
			
//			m.setText(message);
			m.setContent(message,"text/html");
			
			
			
			//send
			
			Transport.send(m);
			
			System.out.println("sent success...........");
			
			
		}catch(Exception e) {
//			e.printStackTrace();
			System.out.println(e);
		}
      return f;
    }
	 
}
