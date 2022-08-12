package com.askarov.bot.telegram.services.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface CommandConsole {

    String getCommandSyntax();

    String execute(Update update);
}
