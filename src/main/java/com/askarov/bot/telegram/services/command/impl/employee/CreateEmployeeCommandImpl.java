package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.statecontroller.BotState;
import com.askarov.bot.telegram.statecontroller.EmployeeDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackData.EMPLOYEE_CREATE;

@Slf4j
@Service
public class CreateEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;
    private final EmployeeDataCache<Long, CallbackData> employeeDataCache;

    @Autowired
    public CreateEmployeeCommandImpl(EmployeeRepository employeeRepository,
                                     EmployeeDataCache<Long, CallbackData> employeeDataCache) {
        this.employeeRepository = employeeRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return EMPLOYEE_CREATE.getCommandName();
    }

    @Override
    public String execute(Update update) {
        String[] empData = update.getMessage().getText().trim().split("\\s");

        Employee employee = Employee.builder()
                .chatId(update.getMessage().getChatId())
                .employeeLastName(empData[0])
                .employeeFirstName(empData[1])
                .employeePatronymic(empData[2])
                .employeePost(empData[3])
                .build();

        employeeRepository.save(employee);
        return "Вы добавлены!";
    }

    public String waitExecute(Update update) {
        employeeDataCache.updateIfPresent(update.getCallbackQuery().getFrom().getId(),
                EMPLOYEE_CREATE);
        return "Введите данные сотрудника по форме: <i>Фамилия Имя Отчество Должность</i>";
    }
}
