package com.guud.company.library.notification.telegram.controller;

import com.guud.company.library.notification.telegram.payload.TelegramRequest;
import com.guud.company.library.notification.telegram.service.TelegramServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1/library/telegram")
@CrossOrigin
@RestController
@Log4j2
public class TelegramController {

    @Autowired
    private TelegramServiceImpl telegramService;

    @PostMapping(value = "/send")
    public String createMail(@RequestBody TelegramRequest telegram){
        telegramService.sendBotMessage(telegram.getChatId(), telegram.getMessage());
        return "telegram";
    }
}
