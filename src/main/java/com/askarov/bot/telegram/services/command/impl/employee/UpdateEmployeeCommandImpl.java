package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.cache.impl.EmployeeDataCacheImpl;
import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.services.handler.text.TextHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class UpdateEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDataCacheImpl<Long, CallbackDataAndBotState> dataCache;

    @Override
    public String getCommandSyntax() {
        return EMPLOYEE_UPDATE.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String reply;
        try {
            String[] empDataUpdate = update.getMessage().getText().trim().split("\\s");
            Employee employee = employeeRepository.getByChatId(chatId);
            if (employee != null) {
                employee.setEmployeeLastName(empDataUpdate[0]);
                employee.setEmployeeFirstName(empDataUpdate[1]);
                employee.setEmployeePatronymic(empDataUpdate[2]);
                employee.setEmployeePost(TextHandler.employeePostToString(empDataUpdate));
                employeeRepository.save(employee);
                reply = "Ваши данные обновлены! ✅";
            } else {
                reply = "Сначала нужно добавиться ❌";
            }
            dataCache.updateIfPresent(chatId, START);
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    dataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось обновить данные ❌";
        }

        log.info("Status {}, Command {}, reply message {}",
                dataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        dataCache.updateIfPresent(chatId, EMPLOYEE_UPDATE);
        return "Введите новые данные по форме:\n<b><i>Фамилия Имя Отчество Должность</i></b>";
    }
}
