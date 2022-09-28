package com.askarov.bot.telegram.services.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    String getCommandSyntax();

    String execute(Update update);

    String waitExecute(Update update);
}
