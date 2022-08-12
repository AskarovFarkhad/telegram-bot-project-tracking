package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.services.command.CommandConsole;
import com.askarov.bot.telegram.services.enums.CallbackData;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class UpdateCommandImpl implements CommandConsole {

    @Override
    public String getCommandSyntax() {
        return CallbackData.EMPLOYEE_UPDATE.getCallbackData();
    }

    @Override
    public String execute(Update update) {
        return "Ваши данные обновлены!";
    }
}
