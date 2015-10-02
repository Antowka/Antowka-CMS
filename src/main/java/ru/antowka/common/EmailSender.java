package ru.antowka.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * Created by Anton Nik on 02.10.15.
 */
@Component
public class EmailSender {

    @Autowired
    private MailSender mailSender;

    @Value("${email.senderAddress}")
    private String fromSend;

    /**
     * Send email to user
     *
     * @param to
     * @param subject
     * @param msg
     */
    public void sendMail(String to, String subject, String msg) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromSend);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(msg);
        mailSender.send(message);
    }
}
