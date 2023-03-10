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
public class CreateEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDataCacheImpl<Long, CallbackDataAndBotState> dataCache;

    @Override
    public String getCommandSyntax() {
        return EMPLOYEE_CREATE.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String reply;

        try {
            String[] empDataCreate = update.getMessage().getText().trim().split("\\s");
            Employee employee = Employee.builder()
                    .chatId(update.getMessage().getChatId())
                    .employeeLastName(empDataCreate[0])
                    .employeeFirstName(empDataCreate[1])
                    .employeePatronymic(empDataCreate[2])
                    .employeePost(TextHandler.employeePostToString(empDataCreate))
                    .build();
            if (employeeRepository.getByChatId(chatId) != null) {
                reply = "Вы уже есть в списке! ✅";
            } else {
                employeeRepository.save(employee);
                reply = "Вы добавлены! ✅";
            }
            dataCache.updateIfPresent(chatId, START);
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    dataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось добавить сотрудника ❌";
        }

        log.info("Status {}, Command {}, reply message {}",
                dataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    public String waitExecute(Update update, Long chatId) {
        dataCache.updateIfPresent(chatId, EMPLOYEE_CREATE);
        return "Введите данные по форме:\n<b><i>Фамилия Имя Отчество Должность</i></b>";
    }
}
