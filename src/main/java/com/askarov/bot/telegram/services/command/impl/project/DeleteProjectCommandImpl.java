package com.askarov.bot.telegram.services.command.impl.project;

import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.cache.EmployeeDataCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.PROJECT_DELETE;
import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.START;

@Slf4j
@Service
public class DeleteProjectCommandImpl implements Command {

    private final ProjectRepository projectRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public DeleteProjectCommandImpl(ProjectRepository employeeRepository,
                                    EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.projectRepository = employeeRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return PROJECT_DELETE.getCommandName();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String[] projectDataDelete = update.getMessage().getText().trim().split("\\s");
        String reply;

        try {
            reply = "Проект удалён! ✅";
            // TODO Не сделано
        } catch (Exception e) {
            log.error("Command {}, Error: {}", this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось удалить проект ❌";
        }

        employeeDataCache.updateIfPresent(chatId, START);
        log.info("Command {}, reply message {}", this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, PROJECT_DELETE);
        return "Введите номер проекта:\n";
    }
}
