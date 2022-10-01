package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.cache.EmployeeDataCache;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class DeleteEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final ProjectRegistrationRepository projectRegistrationRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Override
    public String getCommandSyntax() {
        return EMPLOYEE_DELETE.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String reply;
        Employee employee = employeeRepository.getByChatId(chatId);

        try {
            if (employee != null) {
                projectRegistrationRepository.deleteByEmployee(employee);
                employeeRepository.deleteByChatId(chatId);
                reply = "Вы удалены из списка сотрудников! ✅";
            } else {
                reply = "Вы отсутствуете в списке сотрудников ❌";
            }
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    employeeDataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось удалить сотрудника ❌";
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
