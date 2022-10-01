package com.askarov.bot.telegram.services.command.impl.general;

import com.askarov.bot.telegram.cache.impl.EmployeeDataCacheImpl;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.services.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.START;

@Service
public class StartCommandImpl implements Command {

    EmployeeDataCacheImpl<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public StartCommandImpl(EmployeeDataCacheImpl<Long, CallbackDataAndBotState> employeeDataCache) {
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return START.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        return  "Приветствую тебя, " + update.getMessage().getFrom().getFirstName() + "!";
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.addIfAbsent(chatId, START);
        return execute(update, chatId);
    }
}
