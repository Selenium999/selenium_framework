package com.zomato.utility;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailUtility {

	public static void sendPDFReportByGMail(String from, String pass, String to, String subject, String body) {

		Properties props = System.getProperties();

		String host = "smtp.gmail.com";

		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", host);

		props.put("mail.smtp.user", from);

		props.put("mail.smtp.password", pass);

		props.put("mail.smtp.port", "587");

		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);

		try {

		    //Set from address

		message.setFrom(new InternetAddress(from));

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		//Set subject

		message.setSubject(subject);

		message.setText(body);

		MimeBodyPart objMessageBodyPart = new MimeBodyPart();

		objMessageBodyPart.setText(body);

		Multipart multipart = new MimeMultipart();

		multipart.addBodyPart(objMessageBodyPart);

		objMessageBodyPart = new MimeBodyPart();

		//Set path to the pdf report file

		/* String filename = System.getProperty("C:\\Shwetali\\Eclipse_Workspace\\zomato_TestNG\\test-output\\index.html");

		//Create data source to attach the file in mail

		DataSource source = new FileDataSource(filename); 

		objMessageBodyPart.setDataHandler(new DataHandler(source));

		objMessageBodyPart.setFileName(filename); 

		multipart.addBodyPart(objMessageBodyPart); */
		
		
	        String file = "C:\\Shwetali\\Eclipse_Workspace\\zomato_TestNG\\test-output\\emailable-report.html";
	        String fileName = "emailable-report.html";
	        DataSource source = new FileDataSource(file);
	        objMessageBodyPart.setDataHandler(new DataHandler(source));
	        objMessageBodyPart.setFileName(fileName);
	        multipart.addBodyPart(objMessageBodyPart);


		message.setContent(multipart);

		Transport transport = session.getTransport("smtp");

		transport.connect(host, from, pass);

		transport.sendMessage(message, message.getAllRecipients());

		transport.close();

		}

		catch (AddressException ae) {

		ae.printStackTrace();

		}

		catch (MessagingException me) {

		me.printStackTrace();

		}

		}

}
