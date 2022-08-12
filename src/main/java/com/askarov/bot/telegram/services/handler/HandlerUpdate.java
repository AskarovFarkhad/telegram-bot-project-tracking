package com.askarov.bot.telegram.services.handler;

import com.askarov.bot.telegram.config.BotConfig;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface HandlerUpdate {

    void updateHandler(BotConfig bot, Update update);
}
