package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.cache.impl.AdminDataCacheImpl;
import com.askarov.bot.telegram.cache.impl.EmployeeDataCacheImpl;
import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;
import com.askarov.bot.telegram.services.command.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.EMPLOYEE_DELETE;
import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.START;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class DeleteEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final ProjectRegistrationRepository projectRegistrationRepository;
    private final AdminDataCacheImpl<Long, Boolean> adminAccountDataCache;
    private final EmployeeDataCacheImpl<Long, CallbackDataAndBotState> dataCache;

    @Override
    public String getCommandSyntax() {
        return EMPLOYEE_DELETE.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String reply;

        try {
            if (adminAccountDataCache.get(chatId) != null) {
                String[] empDataDelete = update.getMessage().getText().trim().split("\\s");
                List<Employee> employeeList =
                        employeeRepository.getAllByEmployeeLastNameAndEmployeeFirstNameAndEmployeePatronymic(
                        empDataDelete[0], empDataDelete[1], empDataDelete[2]);
                employeeList.forEach(employee -> {
                    projectRegistrationRepository.deleteByEmployee(employee);
                    employeeRepository.delete(employee);
                });
                reply = "Сотрудник с указанными данными удалён! ✅";
            } else {
                Employee employee = employeeRepository.getByChatId(chatId);
                if (employee != null) {
                    projectRegistrationRepository.deleteByEmployee(employee);
                    employeeRepository.deleteByChatId(chatId);
                    reply = "Вы удалены из списка сотрудников! ✅";
                } else {
                    reply = "Вы отсутствуете в списке сотрудников ❌";
                }
            }
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    dataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось удалить сотрудника ❌";
        }

        dataCache.updateIfPresent(chatId, START);
        log.info("Status {}, Command {}, reply message {}",
                dataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        if (adminAccountDataCache.get(chatId) != null) {
            dataCache.updateIfPresent(chatId, EMPLOYEE_DELETE);
            return "Введите данные по форме:\n<b><i>Фамилия Имя Отчество</i></b>";
        } else {
            dataCache.updateIfPresent(chatId, EMPLOYEE_DELETE);
            return execute(update, chatId);
        }
    }
}
