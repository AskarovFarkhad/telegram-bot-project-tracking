package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class UpdateEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeCommandImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String getCommandSyntax() {
        return CallbackData.EMPLOYEE_UPDATE.getCommandName();
    }

    @Override
    public String execute(Update update) {
        return "Ваши данные обновлены!";
    }
}
