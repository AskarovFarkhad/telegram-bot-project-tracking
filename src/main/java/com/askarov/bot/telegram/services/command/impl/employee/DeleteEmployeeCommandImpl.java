package com.askarov.bot.telegram.services.command.impl.employee;

import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.services.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
public class DeleteEmployeeCommandImpl implements Command {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DeleteEmployeeCommandImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public String getCommandSyntax() {
        return CallbackData.EMPLOYEE_DELETE.getCommandName();
    }

    @Override
    public String execute(Update update) {
        String reply;
        if (employeeRepository.deleteByChatId(update.getCallbackQuery().getFrom().getId()) == 1) {
            reply = "Вы удалены из списка сотрудников!";
        } else {
            reply = "Вы отсутствуете в списке сотрудников...";
        }
        return reply;
    }

    @Override
    public String waitExecute(Update update) {
        return execute(update);
    }
}
