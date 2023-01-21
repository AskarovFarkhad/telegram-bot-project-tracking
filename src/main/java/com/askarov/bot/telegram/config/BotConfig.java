package com.askarov.bot.telegram.config;

import com.askarov.bot.telegram.services.handler.update.UpdateHandler;
import com.askarov.bot.telegram.util.keyboard.ReplyKeyboard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Component
@PropertySource("classpath:bot.properties")
public class BotConfig extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String BOT_NAME;
    @Value("${bot.token}")
    private String BOT_TOKEN;

    private final UpdateHandler handler;

    @Autowired
    public BotConfig(UpdateHandler handler) {
        this.handler = handler;
        ReplyKeyboard.initReplyKeyboard();
    }

    @Override
    public void onUpdateReceived(Update update) {
        outMessageExecute(handler.updateHandler(update));
    }

    private void outMessageExecute(SendMessage outMsg) {
        try {
            this.execute(outMsg);
        } catch (TelegramApiException e) {
            log.error("Error: {}", e.getMessage());
        }
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
