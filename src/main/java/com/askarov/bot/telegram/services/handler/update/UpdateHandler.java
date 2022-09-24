package com.askarov.bot.telegram.services.handler.update;

import com.askarov.bot.telegram.config.BotConfig;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface UpdateHandler {

    void updateHandler(BotConfig bot, Update update);
}
