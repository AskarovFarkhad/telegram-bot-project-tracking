package com.askarov.bot.telegram.services.handler.query;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface QueryHandler {

    SendMessage outMessageButton(Update update, String msg, Long chatId);
}
