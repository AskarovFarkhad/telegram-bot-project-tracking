package com.askarov.bot.telegram.services.command.impl.general;

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
    public String execute(Update update, Long chatId) {
        return "Я не понимаю, что ты хочешь от меня";
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        // TODO return null ?
        return null;
    }
}
