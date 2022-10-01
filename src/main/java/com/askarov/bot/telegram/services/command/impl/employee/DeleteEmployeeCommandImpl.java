package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.cache.EmployeeDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
public class DeleteEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public DeleteEmployeeCommandImpl(EmployeeRepository employeeRepository,
                                     EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.employeeRepository = employeeRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return EMPLOYEE_DELETE.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String reply;
        if (employeeRepository.deleteByChatId(chatId) == 1) {
            reply = "Вы удалены из списка сотрудников! ✅";
        } else {
            reply = "Вы отсутствуете в списке сотрудников ❌";
        }
        employeeDataCache.updateIfPresent(chatId, START);
        log.info("Status {}, Command {}, reply message {}",
                employeeDataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, EMPLOYEE_DELETE);
        return execute(update, chatId);
    }
}
