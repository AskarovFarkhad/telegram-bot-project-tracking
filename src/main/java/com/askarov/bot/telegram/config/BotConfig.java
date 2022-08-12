package com.askarov.bot.telegram.config;

import com.askarov.bot.telegram.services.menu.keyboard.ReplyKeyboard;
import com.askarov.bot.telegram.services.handler.HandlerUpdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
@PropertySource("classpath:bot.properties")
public class BotConfig extends TelegramLongPollingBot {

    @Value("${bot.getName}")
    private String BOT_NAME;
    @Value("${bot.getToken}")
    private String BOT_TOKEN;

    private final HandlerUpdate handler;

    @Autowired
    public BotConfig(HandlerUpdate handler) {
        this.handler = handler;
        ReplyKeyboard.initReplyKeyboard();
    }

    @Override
    public void onUpdateReceived(Update update) {
        handler.updateHandler(this, update);
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}
