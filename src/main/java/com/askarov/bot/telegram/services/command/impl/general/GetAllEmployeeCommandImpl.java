package com.askarov.bot.telegram.services.command.impl.general;

import com.askarov.bot.telegram.cache.impl.EmployeeDataCacheImpl;
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
    private final EmployeeDataCacheImpl<Long, CallbackDataAndBotState> dataCache;

    @Override
    public String getCommandSyntax() {
        return SEARCH_ALL_EMPLOYEES.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String reply;

        try {
            List<Employee> employeeList = employeeRepository.findAll();
            if (employeeList.size() != 0) {
                reply = EmployeePrintHandler.printData(employeeList, projectRegistrationRepository);
            } else {
                reply = "Список сотрудников пуст ❌";
            }
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    dataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Возникла ошибка... ❌";
        }

        dataCache.updateIfPresent(chatId, START);
        log.info("Status {}, Command {}, reply message {}",
                dataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    public String waitExecute(Update update, Long chatId) {
        dataCache.updateIfPresent(chatId, SEARCH_ALL_EMPLOYEES);
        return execute(update, chatId);
    }
}
