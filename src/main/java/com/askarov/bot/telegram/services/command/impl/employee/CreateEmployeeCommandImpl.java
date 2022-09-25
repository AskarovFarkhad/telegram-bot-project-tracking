package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
public class CreateEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;

    public CreateEmployeeCommandImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String getCommandSyntax() {
        return CallbackData.EMPLOYEE_CREATE.getCommandName();
    }

    @Override
    public String execute(Update update) {

        Employee employee = Employee.builder()
                .employeeChatId(update.getCallbackQuery().getFrom().getId())
                .employeeFirstName(update.getCallbackQuery().getFrom().getFirstName())
                .employeeLastName("---")
                .employeePatronymic("---")
                .employeePost("Инженер")
                .build();

        employeeRepository.save(employee);
        return "Вы добавлены!";
    }
}
