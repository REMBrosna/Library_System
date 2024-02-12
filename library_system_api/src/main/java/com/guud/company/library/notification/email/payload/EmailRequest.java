package com.guud.company.library.notification.email.payload;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
public class EmailRequest {

    @Email
    @NotNull
    @Size(min = 1, message = "Please, set an email address to send the message to it")
    private String recipient;
    private String subject;
    private String content;
}
