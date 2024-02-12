package com.guud.company.library.notification.email.service;

import com.guud.company.library.enums.EnumContentType;
import com.guud.company.library.notification.param.EmailParam;
import com.guud.company.library.notification.template.model.TNotificationTemplate;
import com.guud.company.library.notification.template.service.NotificationTemplateService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;

@Service
@Log4j2
public class EmailServiceImpl{

    private final NotificationTemplateService templateService;
    private final JavaMailSender emailSender;

    @Autowired
    public EmailServiceImpl(NotificationTemplateService templateService, JavaMailSender emailSender) {
        this.templateService = templateService;
        this.emailSender = emailSender;
    }

    public void notify(EmailParam emailParam) {
        try {
            TNotificationTemplate notificationTemplate = templateService.getTNotificationTemplateByTemChannelType(emailParam.getTemplateId());
            if (notificationTemplate.getNotContentType().equalsIgnoreCase(EnumContentType.HTML.name())){
                MimeMessage message = emailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
                helper.setTo(emailParam.getTo());
                helper.setCc(emailParam.getCc());
                helper.setSubject(notificationTemplate.getNotSubject());
                helper.setText(updateContent(emailParam, notificationTemplate.getNotContent()), true);
                emailSender.send(message);
            } else {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setTo(emailParam.getTo());
                message.setCc(emailParam.getCc());
                message.setSubject(notificationTemplate.getNotSubject());
                message.setText(updateContent(emailParam, notificationTemplate.getNotContent()));
                emailSender.send(message);
            }
        } catch (MailException | MessagingException exception) {
            log.error("Error Notify Email ", exception);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        } catch (MailException exception) {
            log.error("sendSimpleMessage ", exception);
        }
    }

    private String updateContent(EmailParam emailParam, String template){
        HashMap<String, String> hmFields = emailParam.getContentFields();
        for (String key : hmFields.keySet()) {
            String value = hmFields.get(key);
            template = template.replaceAll(key, value);
        }
        return template;
    }

    public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource file = new FileSystemResource(new File(pathToAttachment));
            helper.addAttachment("Invoice", file);
            emailSender.send(message);
        } catch (MessagingException e) {
            log.error("sendMessageWithAttachment", e);
        }
    }
}