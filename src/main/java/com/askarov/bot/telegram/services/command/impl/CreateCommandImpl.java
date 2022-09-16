package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.entity.Project;
import com.askarov.bot.telegram.enums.StatusProject;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.CommandConsole;
import com.askarov.bot.telegram.enums.CallbackData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
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
