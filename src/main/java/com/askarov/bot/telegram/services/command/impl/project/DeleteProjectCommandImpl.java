package com.askarov.bot.telegram.services.command.impl.project;

import com.askarov.bot.telegram.cache.EmployeeDataCache;
import com.askarov.bot.telegram.entity.Project;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.Command;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
public class DeleteProjectCommandImpl implements Command {

    private final ProjectRepository projectRepository;
    private final ProjectRegistrationRepository projectRegistrationRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public DeleteProjectCommandImpl(ProjectRepository employeeRepository,
                                    ProjectRegistrationRepository projectRegistrationRepository,
                                    EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.projectRepository = employeeRepository;
        this.projectRegistrationRepository = projectRegistrationRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return PROJECT_DELETE.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String[] projectDataDelete = update.getMessage().getText().trim().split("\\s");
        String reply;
        Project project = projectRepository.getByProjectNumber(projectDataDelete[0]);
        try {
            if (project != null) {
                projectRegistrationRepository.deleteByProject(project);
                projectRepository.delete(project);
                reply = "Проект удалён! ✅";
            } else {
                reply = "Проект отсутствует! ✅";
            }
            employeeDataCache.updateIfPresent(chatId, START);
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    employeeDataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось удалить проект ❌";
        }

        log.info("Status {}, Command {}, reply message {}",
                employeeDataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, PROJECT_DELETE);
        return "Введите номер проекта:\n";
    }
}
