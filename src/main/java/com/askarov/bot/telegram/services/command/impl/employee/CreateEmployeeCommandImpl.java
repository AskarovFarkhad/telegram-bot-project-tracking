package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.cache.EmployeeDataCache;
import com.askarov.bot.telegram.services.handler.text.TextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.EMPLOYEE_CREATE;
import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.START;

@Slf4j
@Service
public class CreateEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public CreateEmployeeCommandImpl(EmployeeRepository employeeRepository,
                                     EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.employeeRepository = employeeRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return EMPLOYEE_CREATE.getCommandName();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String[] empDataCreate = update.getMessage().getText().trim().split("\\s");
        String reply;

        try {
            Employee employee = Employee.builder()
                    .chatId(update.getMessage().getChatId())
                    .employeeLastName(empDataCreate[0])
                    .employeeFirstName(empDataCreate[1])
                    .employeePatronymic(empDataCreate[2])
                    .employeePost(TextHandler.employeePostToString(empDataCreate))
                    .build();
            if (employeeRepository.getByChatId(chatId) != null) {
                reply = "Вы уже есть в списке! ✅";
                employeeDataCache.updateIfPresent(chatId, START);
            } else {
                employeeRepository.save(employee);
                employeeDataCache.updateIfPresent(chatId, START);
                reply = "Вы добавлены! ✅";
            }
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}", employeeDataCache.get(chatId),
                    this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось добавить сотрудника ❌";
        }

        log.info("Command {}, reply message {}", this.getCommandSyntax(), reply);
        return reply;
    }

    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, EMPLOYEE_CREATE);
        return "Введите данные по форме:\n<b><i>Фамилия Имя Отчество Должность</i></b>";
    }
}
