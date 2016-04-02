package ru.antowka.service.impl;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;
import ru.antowka.service.EmailSenderService;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for send alerts to email
 */
@Service
public class EmailSenderServiceImpl implements EmailSenderService{

    /**
     * Email Sender
     */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * set Email address for sender
     */
    @Value("${email.senderAddress}")
    private String fromSend;

    /**
     * Template engine
     */
    @Autowired
    private VelocityEngine velocityEngine;

    @Async
    public void ticketPublic(String email){

    }

    /**
     * Method generate alert about new Ticket
     *
     * @param email - email recipient
     */
    @Async
    public void newTicketCreated(String email){

        //For Admin
        sendMail(mimeMessage -> {

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo("662307@gmail.com");
            message.setSubject("Новая жалоба");
            message.setFrom(this.fromSend);
            Map<String, String> model = new HashMap<String, String>();
            model.put("email", email);

            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "./templates/email/notificationAboutNewTicketToAdm.vm", model);

            message.setText(text, true);
        });


        //For User
        sendMail(mimeMessage -> {

            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(email);
            message.setSubject("Ваша жалоба принята к рассмотрению");
            message.setFrom(this.fromSend);
            Map<String, String> model = new HashMap<String, String>();
            model.put("email", email);

            String text = VelocityEngineUtils.mergeTemplateIntoString(
                    velocityEngine, "./templates/email/ticketCreate.vm", model);

            message.setText(text, true);
        });
    }

    /**
     * Send email to user
     *
     * @param preparator
     */
    private void sendMail(MimeMessagePreparator preparator) {

        try {
            this.mailSender.send(preparator);
        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
