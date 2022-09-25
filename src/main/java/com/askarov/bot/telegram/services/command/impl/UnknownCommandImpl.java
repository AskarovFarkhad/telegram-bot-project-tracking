package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.services.command.Command;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UnknownCommandImpl implements Command {

    @Override
    public String getCommandSyntax() {
        return null;
    }

    @Override
    public String execute(Update update) {
        return "Я не понимаю, что ты хочешь от меня";
    }
}
