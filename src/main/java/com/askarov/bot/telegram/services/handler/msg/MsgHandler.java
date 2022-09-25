package com.askarov.bot.telegram.services.handler.msg;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface MsgHandler {

    SendMessage outMessageText(Update update, String msg, Long chatId);
}
