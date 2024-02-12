package com.guud.company.library.notification.telegram.service;

import com.guud.company.library.notification.param.TelegramParam;
import com.guud.company.library.notification.template.model.TNotificationTemplate;
import com.guud.company.library.notification.template.service.NotificationTemplateService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import java.util.HashMap;

@Service
@ComponentScan(basePackages = { "com.organization.library" })
@PropertySource(value={"classpath:application.properties"})
@Log4j2
public class TelegramServiceImpl extends TelegramLongPollingBot {

    @Value("${telegram.bot.username}")
    private String telegramBotUsername;
    @Value("${telegram.bot.token}")
    private String telegramBotToken;

    @Autowired
    private NotificationTemplateService templateService;

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return telegramBotUsername;
    }

    @Override
    public String getBotToken() {
        return telegramBotToken;
    }

    public void notify(TelegramParam telegramParam) throws Exception {
        SendMessage response = new SendMessage();
        TNotificationTemplate notificationTemplate = templateService.getTNotificationTemplateByTemChannelType(telegramParam.getTemplateId());
        response.setChatId(telegramParam.getChatId());
        response.setText(updateContent(telegramParam, notificationTemplate.getNotContent()));
        try {
            sendMessage(response);
        } catch (TelegramApiException e) {
            log.error("Error Notify Telegram", e);
        }
    }

    private String updateContent(TelegramParam telegramParam, String template){
        HashMap<String, String> hmFields = telegramParam.getContentFields();
        for (String key : hmFields.keySet()) {
            String value = hmFields.get(key);
            template = template.replaceAll(key, value);
        }
        return template;
    }

    public void sendBotMessage(Long chatId, String message){
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText(message);
        try {
            sendMessage(response);
        } catch (TelegramApiException e) {
            log.error("Error Notify Telegram", e);
        }
    }
}
