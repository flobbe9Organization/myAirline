package com.example.myAirline.config;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;


/**
 * Configuration for seding an email.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Configuration
public class MailConfig {
    
    @Autowired
    private JavaMailSender javaMailSender;

    /** Adress from which to send email. */
    public static final String SENDER_EMAILADRESS = "schikarski98@gmail.com";

    
    /**
     * Sends email from 'schikarski98@gmail.com'.
     * 
     * @param to target adress to send the mail to.
     * @param subject the subject of the mail.
     * @param text the content in html format.
     * @throws MessagingException
     */
    public void send(String to, String subject, String text, File attachment) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");

        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setFrom(SENDER_EMAILADRESS);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(text, true);
        if (attachment != null)
            mimeMessageHelper.addAttachment(attachment.getName(), attachment);
        
        javaMailSender.send(mimeMessage);
    }
}