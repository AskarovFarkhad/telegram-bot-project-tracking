package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.cache.EmployeeDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.EMPLOYEE_UPDATE;
import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.START;

@Slf4j
@Service
public class UpdateEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public UpdateEmployeeCommandImpl(EmployeeRepository employeeRepository,
                                     EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.employeeRepository = employeeRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return EMPLOYEE_UPDATE.getCommandName();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String[] empDataUpdate = update.getMessage().getText().trim().split("\\s");
        String reply;

        try {
            Employee employee = employeeRepository.getByChatId(chatId);
            if (employee != null) {
                employee.setEmployeeLastName(empDataUpdate[0]);
                employee.setEmployeeFirstName(empDataUpdate[1]);
                employee.setEmployeePatronymic(empDataUpdate[2]);
                employee.setEmployeePost(empDataUpdate[3]);
                employeeRepository.save(employee);
                reply = "Ваши данные обновлены! ✅";
                employeeDataCache.updateIfPresent(chatId, START);
            } else {
                reply = "Не удалось обновить данные ❌";
            }
        } catch (Exception e) {
            log.error("Command {}, Error: {}", this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось обновить данные ❌";
        }

        log.info("Command {}, reply message {}", this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, EMPLOYEE_UPDATE);
        return "Введите новые данные по форме:\n<b><i>Фамилия Имя Отчество Должность</i></b>";
    }
}
