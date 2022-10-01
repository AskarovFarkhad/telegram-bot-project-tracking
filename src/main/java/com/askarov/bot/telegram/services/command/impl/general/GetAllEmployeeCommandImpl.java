package com.askarov.bot.telegram.services.command.impl.general;

import com.askarov.bot.telegram.cache.EmployeeDataCache;
import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.services.handler.print.EmployeePrintHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
public class GetAllEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final ProjectRegistrationRepository projectRegistrationRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public GetAllEmployeeCommandImpl(EmployeeRepository employeeRepository,
                                     ProjectRegistrationRepository projectRegistrationRepository,
                                     EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.employeeRepository = employeeRepository;
        this.projectRegistrationRepository = projectRegistrationRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return SEARCH_ALL_EMPLOYEES.getCommandName();
    }

    @Override
    public String execute(Update update, Long chatId) {
        List<Employee> employeeList = employeeRepository.findAll();

        String reply = EmployeePrintHandler.printData(employeeList, projectRegistrationRepository);

        log.info("Command {}, reply message {}", this.getCommandSyntax(), reply);
        employeeDataCache.updateIfPresent(chatId, START);
        return reply;
    }

    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, SEARCH_ALL_EMPLOYEES);
        return execute(update, chatId);
    }
}
