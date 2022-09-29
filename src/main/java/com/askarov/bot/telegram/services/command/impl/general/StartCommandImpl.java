package com.askarov.bot.telegram.services.command.impl.general;

import com.askarov.bot.telegram.services.command.Command;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class StartCommandImpl implements Command {

    @Override
    public String getCommandSyntax() {
        return "/start";
    }

    @Override
    public String execute(Update update, Long chatId) {
        return  "Приветствую тебя, " + update.getMessage().getFrom().getFirstName() + "!";
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        return null;
    }
}