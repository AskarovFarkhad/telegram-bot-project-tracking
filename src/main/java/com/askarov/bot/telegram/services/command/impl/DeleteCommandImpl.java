package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.services.command.CommandConsole;
import com.askarov.bot.telegram.enums.CallbackData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class DeleteCommandImpl implements CommandConsole {

    @Override
    public String getCommandSyntax() {
        return CallbackData.EMPLOYEE_DELETE.getCallbackData();
    }

    @Override
    public String execute(Update update) {
        return "Вы удалены из списка сотрудников!";
    }
}
