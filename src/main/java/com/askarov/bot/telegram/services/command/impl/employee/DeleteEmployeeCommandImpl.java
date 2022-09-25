package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
public class DeleteEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;

    public DeleteEmployeeCommandImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String getCommandSyntax() {
        return CallbackData.EMPLOYEE_DELETE.getCommandName();
    }

    @Override
    public String execute(Update update) {
        //TODO: нужно сделать запрос удаления сотрудника по getId
        employeeRepository.deleteById(update.getCallbackQuery().getFrom().getId());
        return "Вы удалены из списка сотрудников!";
    }
}
