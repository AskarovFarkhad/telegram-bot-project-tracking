package com.askarov.bot.telegram.services.handler.update.impl;

import com.askarov.bot.telegram.services.handler.msg.impl.MsgHandlerImpl;
import com.askarov.bot.telegram.services.handler.update.UpdateHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UpdateHandlerImpl implements UpdateHandler {

    private final MsgHandlerImpl msgHandler;

    @Override
    public SendMessage updateHandler(Update update) {
        SendMessage replyMessage = null;

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Long chatId = message.getChatId();
            log.info("New message from User: {}, chatId: {}, with text: {}",
                    message.getFrom().getUserName(), chatId, message.getText());
            replyMessage = msgHandler.outMessageText(update, message.getText(), chatId);
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            Long chatId = callbackQuery.getFrom().getId();
            log.info("New CallbackQuery from User: {}, userId:{}, data:{}",
                    callbackQuery.getFrom().getUserName(), chatId, callbackQuery.getData());
            replyMessage = msgHandler.outMessageText(update, callbackQuery.getData(), chatId);
        }

        return replyMessage;
    }
}
