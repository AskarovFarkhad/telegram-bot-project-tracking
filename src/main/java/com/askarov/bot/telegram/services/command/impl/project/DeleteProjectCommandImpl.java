package com.askarov.bot.telegram.services.command.impl.project;

import com.askarov.bot.telegram.cache.impl.AdminDataCacheImpl;
import com.askarov.bot.telegram.cache.impl.EmployeeDataCacheImpl;
import com.askarov.bot.telegram.entity.Project;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.repository.ProjectRegistrationRepository;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class DeleteProjectCommandImpl implements Command {

    private final ProjectRepository projectRepository;
    private final ProjectRegistrationRepository projectRegistrationRepository;
    private final EmployeeDataCacheImpl<Long, CallbackDataAndBotState> dataCache;
    private final AdminDataCacheImpl<Long, Boolean> adminAccountDataCache;

    @Override
    public String getCommandSyntax() {
        return PROJECT_DELETE.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String reply;
        try {
            String[] projectDataDelete = update.getMessage().getText().trim().split("\\s");
            Project project = projectRepository.getByProjectNumber(projectDataDelete[0]);
            if (project != null) {
                if (adminAccountDataCache.get(chatId) != null) {
                    projectRegistrationRepository.deleteByProject(project);
                    projectRepository.delete(project);
                    reply = "Проект удалён! ✅";
                } else {
                    reply = "Отказано в доступе ❌";
                }
            } else {
                reply = "Проект отсутствует ! ✅";
            }
            dataCache.updateIfPresent(chatId, START);
        } catch (Exception e) {
            log.error("Status {}, Command {}, Error: {}",
                    dataCache.get(chatId), this.getCommandSyntax(), e.getMessage());
            reply = "Не удалось удалить проект ❌";
        }

        log.info("Status {}, Command {}, reply message {}",
                dataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        dataCache.updateIfPresent(chatId, PROJECT_DELETE);
        return "Введите номер проекта:\n";
    }
}
