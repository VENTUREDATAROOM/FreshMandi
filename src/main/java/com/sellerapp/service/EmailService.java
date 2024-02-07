package com.sellerapp.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EmailService {

    private org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmailService.class);
    
    

    public boolean sendEmail(String subject, String message, String to) {
        boolean flag = false;
        String from = "kansalgorank56@gmail.com";
        if (to != null) {
            String host = "smtp.gmail.com";

            // get the system properties
            Properties properties = System.getProperties();
            System.out.println("PROPERTIES" + properties);

            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            // properties.put("mail.debug", "true");

            // to get the session object..
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("kansalgorank56@gmail.com", "veklkcbjpjzwbyyl");
                }
            });
            session.setDebug(true);

            // compose the message [text, multi-media]
            MimeMessage mimeMessage = new MimeMessage(session);

            try {
                mimeMessage.setFrom(from);

                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                mimeMessage.setSubject(subject);

                // Create MimeMultipart
                MimeMultipart mimeMultipart = new MimeMultipart();

                // Text part
                MimeBodyPart textMime = new MimeBodyPart();
                //Context context = new Context();
                //context.setVariable("otp", otp);
               // textMime.setContent(templateEngine.process("email-template", context), "text/html");
                //textMime.setContent(generateEmailContent(otp),"text/html");
                textMime.setText(message,"UTF-8","html");
                mimeMultipart.addBodyPart(textMime);

                // Image part
                String path = "C:\\Users\\GORANK\\Downloads\\logoimg.jpg";
                Path pathToImage = Paths.get(path);
                if (Files.exists(pathToImage) && Files.isRegularFile(pathToImage)) {
                    byte[] imageBytes = Files.readAllBytes(pathToImage);
                    String base64encodedimage = Base64.getEncoder().encodeToString(imageBytes);

                    MimeBodyPart imageMime = new MimeBodyPart();
                    imageMime.setContent(base64encodedimage, "text/jpeg");
                    mimeMultipart.addBodyPart(imageMime);
                }

                // Set the content
                mimeMessage.setContent(mimeMultipart);

                // Send the message
                Transport.send(mimeMessage);
                log.info("Sent email successfully");
                flag = true;
            } catch (Exception e) {
                log.error("Error sending email", e);
            }
        } else {
            log.warn("To address is null. Email not sent.");
        }
        return flag;
    }
    

}
