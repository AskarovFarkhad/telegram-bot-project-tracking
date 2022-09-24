package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.CommandConsole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
public class CreateCommandImpl implements CommandConsole {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public CreateCommandImpl(EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String getCommandSyntax() {
        return CallbackData.EMPLOYEE_CREATE.getCallbackData();
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
