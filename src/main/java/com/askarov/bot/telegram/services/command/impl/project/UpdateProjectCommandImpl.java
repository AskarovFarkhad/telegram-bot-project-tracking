package com.askarov.bot.telegram.services.command.impl.project;

import com.askarov.bot.telegram.cache.EmployeeDataCache;
import com.askarov.bot.telegram.entity.Project;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.services.handler.text.TextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
public class UpdateProjectCommandImpl implements Command {

    private final ProjectRepository projectRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public UpdateProjectCommandImpl(ProjectRepository projectRepository,
                                    EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.projectRepository = projectRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return PROJECT_UPDATE.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String[] projectDataUpdate = update.getMessage().getText().trim().split("\\s");
        String reply;

        try {
            Project project = projectRepository.getByProjectNumber(projectDataUpdate[0]);
            if (projectRepository.getByProjectNumber(project.getProjectNumber()) != null) {
                project.setProjectName(TextHandler.projectNameToString(projectDataUpdate));
                projectRepository.save(project);
                reply = "Проект обновлён ✅";
            } else {
                reply = "Нет проекта с таким номером ✅";
            }
            employeeDataCache.updateIfPresent(chatId, START);
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    employeeDataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось обновить проект ❌";
        }

        log.info("Status {}, Command {}, reply message {}",
                employeeDataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, PROJECT_UPDATE);
        return "Введите новое название проекта:\n<b><i>Номер Новое название</i></b>";
    }
}
