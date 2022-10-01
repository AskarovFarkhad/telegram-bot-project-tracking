package com.askarov.bot.telegram.services.command.impl.general;

import com.askarov.bot.telegram.cache.impl.EmployeeDataCacheImpl;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.services.command.Command;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.START;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class StartCommandImpl implements Command {

    EmployeeDataCacheImpl<Long, CallbackDataAndBotState> employeeDataCache;

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
