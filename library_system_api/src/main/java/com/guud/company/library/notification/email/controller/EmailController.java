package com.guud.company.library.notification.email.controller;

import com.guud.company.library.notification.email.payload.EmailRequest;
import com.guud.company.library.notification.email.service.EmailServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/library/emails")
@CrossOrigin
@RestController
@Log4j2
public class EmailController {

    @Autowired
    public EmailServiceImpl emailService;

    @Value("${attachment.invoice}")
    private String attachmentPath;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String createMail(@RequestBody EmailRequest mailObject) {
        emailService.sendSimpleMessage(mailObject.getRecipient(),
                mailObject.getSubject(), mailObject.getContent());

        return "emails";
    }

    @RequestMapping(value = "/sendAttachment", method = RequestMethod.POST)
    public String createMailWithAttachment(@RequestBody EmailRequest mailObject) {
        emailService.sendMessageWithAttachment(
                mailObject.getRecipient(),
                mailObject.getSubject(),
                mailObject.getContent(),
                attachmentPath
        );

        return "redirect:/mail";
    }
}
