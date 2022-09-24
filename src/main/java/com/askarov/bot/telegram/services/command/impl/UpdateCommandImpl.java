package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.services.command.CommandConsole;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
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
