package com.askarov.bot.telegram.services.command.impl.project;

import com.askarov.bot.telegram.entity.Employee;
import com.askarov.bot.telegram.entity.Project;
import com.askarov.bot.telegram.entity.ProjectRegistration;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.cache.EmployeeDataCache;
import com.askarov.bot.telegram.services.handler.text.TextHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
public class AddProjectCommandImpl implements Command {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRegistrationRepository projectRegistrationRepository;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Autowired
    public AddProjectCommandImpl(ProjectRepository projectRepository,
                                 EmployeeRepository employeeRepository,
                                 ProjectRegistrationRepository projectRegistrationRepository,
                                 EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache) {
        this.projectRepository = projectRepository;
        this.employeeRepository = employeeRepository;
        this.projectRegistrationRepository = projectRegistrationRepository;
        this.employeeDataCache = employeeDataCache;
    }

    @Override
    public String getCommandSyntax() {
        return PROJECT_ADD.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String[] projectDataCreate = update.getMessage().getText().trim().split("\\s");
        String reply;

        try {
            Employee employee = employeeRepository.getByChatId(update.getMessage().getChatId());

            Project project = Project.builder()
                    .projectNumber(projectDataCreate[0])
                    .projectName(TextHandler.projectNameToString(projectDataCreate))
                    .build();

            ProjectRegistration projectRegistration = ProjectRegistration.builder()
                    .employee(employee)
                    .project(project)
                    .registeredDate(LocalDate.now())
                    .build();

            if (employee == null) {
                reply = "Сначала добавьте сотрудника \uD83D\uDD04";
            } else if (projectRepository.getByProjectNumber(project.getProjectNumber()) != null) {
                projectRegistrationRepository.save(projectRegistration);
                reply = "Проект зарегистрирован ✅";
            } else {
                projectRepository.save(project);
                projectRegistrationRepository.save(projectRegistration);
                reply = "Проект зарегистрирован ✅";
            }
            employeeDataCache.updateIfPresent(chatId, START);
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    employeeDataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось добавить проект ❌";
        }

        log.info("Status {}, Command {}, reply message {}",
                employeeDataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    public String waitExecute(Update update, Long chatId) {
        employeeDataCache.updateIfPresent(chatId, PROJECT_ADD);
        return "Введите данные проекта по форме:\n<b><i>Проект(номер.год) Название</i></b>";
    }
}
