package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.services.command.Command;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CancelCommandImpl implements Command {

    @Override
    public String getCommandSyntax() {
        return "/cancel";
    }

    @Override
    public String execute(Update update) {
        return "Вы вернулись в главное меню ✅";
    }

    @Override
    public String waitExecute(Update update) {
        return null;
    }
}
