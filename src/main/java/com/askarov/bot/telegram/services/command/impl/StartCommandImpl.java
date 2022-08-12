package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.services.command.CommandConsole;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommandImpl implements CommandConsole {

    @Override
    public String getCommandSyntax() {
        return "/start";
    }

    @Override
    public String execute(Update update) {
        return  "Приветствую тебя, " + update.getMessage().getFrom().getFirstName() + "!";
    }
}
