package com.askarov.bot.telegram.services.command.impl.general;

import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.cache.EmployeeDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.SEARCH_EMPLOYEE;

@Slf4j
@Service
public class SearchEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public SearchEmployeeCommandImpl(EmployeeRepository employeeRepository,
                                     EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.employeeRepository = employeeRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return SEARCH_EMPLOYEE.getCommandName();
    }

    @Override
    public String execute(Update update, Long chatId) {
        // TODO Не сделано
        String[] empDataCreate = update.getMessage().getText().trim().split("\\s");
        return null;
    }

    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, SEARCH_EMPLOYEE);
        return "Введите данные по форме:\n<b><i>Фамилия Имя Отчество</i></b>";
    }
}
