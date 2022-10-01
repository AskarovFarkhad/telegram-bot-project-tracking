package com.askarov.bot.telegram.services.command.impl.general;

import com.askarov.bot.telegram.cache.EmployeeDataCache;
import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.services.handler.print.EmployeePrintHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class GetAllEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final ProjectRegistrationRepository projectRegistrationRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Override
    public String getCommandSyntax() {
        return SEARCH_ALL_EMPLOYEES.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        List<Employee> employeeList = employeeRepository.findAll();
        String reply;

        if (employeeList.size() != 0) {
            reply = EmployeePrintHandler.printData(employeeList, projectRegistrationRepository);
        } else {
            reply = "Список сотрудников пуст ❌";
        }

        employeeDataCache.updateIfPresent(chatId, START);
        log.info("Status {}, Command {}, reply message {}",
                employeeDataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, SEARCH_ALL_EMPLOYEES);
        return execute(update, chatId);
    }
}
