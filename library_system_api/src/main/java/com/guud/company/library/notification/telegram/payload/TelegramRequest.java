package com.guud.company.library.notification.telegram.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TelegramRequest {
    @NotNull
    Long chatId;
    @NotBlank
    String message;

    public TelegramRequest() {
    }

    public TelegramRequest(@NotNull Long chatId, @NotBlank String message) {
        this.chatId = chatId;
        this.message = message;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
