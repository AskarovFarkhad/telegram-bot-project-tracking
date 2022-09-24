package com.askarov.bot.telegram.services.handler.update.impl;

import com.askarov.bot.telegram.config.BotConfig;
import com.askarov.bot.telegram.services.handler.query.impl.QueryHandlerImpl;
import com.askarov.bot.telegram.services.handler.msg.impl.MsgHandlerImpl;
import com.askarov.bot.telegram.services.handler.update.UpdateHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UpdateHandlerImpl implements UpdateHandler {

    private MsgHandlerImpl msgHandler;
    private QueryHandlerImpl queryHandler;

    @Override
    public void updateHandler(BotConfig bot, Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            log.info("Bot has callback message: " + update.getMessage());
            outMessageExecute(bot,
                    msgHandler.outMessageText(
                            update,
                            update.getMessage().getText(),
                            update.getMessage().getChatId()));

        } else if (update.hasCallbackQuery()) {
            log.info("Bot has callback query: " + update.getCallbackQuery().getData());
            outMessageExecute(bot,
                    queryHandler.outMessageButton(
                            update,
                            update.getCallbackQuery().getData(),
                            update.getCallbackQuery().getFrom().getId()));
        }
    }

    private void outMessageExecute(BotConfig bot, SendMessage outMsg) {
        try {
            bot.execute(outMsg);
        } catch (TelegramApiException e) {
           log.error("Error: " +  e.getMessage());
        }
    }
}
