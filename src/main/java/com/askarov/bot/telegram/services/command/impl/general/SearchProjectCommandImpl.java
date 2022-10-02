package com.askarov.bot.telegram.services.command.impl.general;

import com.askarov.bot.telegram.cache.impl.EmployeeDataCacheImpl;
import com.askarov.bot.telegram.entity.Project;
import com.askarov.bot.telegram.entity.ProjectRegistration;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class SearchProjectCommandImpl implements Command {

    private final ProjectRepository projectRepository;
    private final ProjectRegistrationRepository projectRegistrationRepository;
    private final EmployeeDataCacheImpl<Long, CallbackDataAndBotState> dataCache;

    @Override
    public String getCommandSyntax() {
        return SEARCH_PROJECT.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        StringBuilder reply = new StringBuilder();

        try {
            String[] projectData = update.getMessage().getText().trim().split("\\s");
            Project project = projectRepository.getByProjectNumber(projectData[0]);
            List<ProjectRegistration> projectRegList = projectRegistrationRepository.getByProject(project);

            if (projectRegList.size() != 0) {
                projectRegList.forEach(projectRegistration -> reply.append(projectRegistration.getEmployee()));
            } else if (project != null) {
                reply.append(project).append("Проектом никто не занимается \uD83D\uDD04");
            } else {
                reply.append("Проект не найден ❌");
            }
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    dataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply.append("Возникла ошибка... ❌");
        }

        dataCache.updateIfPresent(chatId, START);
        log.info("Status {}, Command {}, reply message {}",
                dataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply.toString();
    }

    public String waitExecute(Update update, Long chatId) {
        dataCache.updateIfPresent(chatId, SEARCH_PROJECT);
        return "Введите номер проекта:\n";
    }
}
