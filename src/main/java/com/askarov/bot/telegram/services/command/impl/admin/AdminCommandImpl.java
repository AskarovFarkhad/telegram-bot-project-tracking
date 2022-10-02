package com.askarov.bot.telegram.services.command.impl.admin;

import com.askarov.bot.telegram.cache.impl.AdminDataCacheImpl;
import com.askarov.bot.telegram.cache.impl.EmployeeDataCacheImpl;
import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.services.admin.impl.AdminAccountImpl;
import com.askarov.bot.telegram.services.command.Command;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.ADMIN;
import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.START;

@Slf4j
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class AdminCommandImpl implements Command {

    private final EmployeeDataCacheImpl<Long, CallbackDataAndBotState> dataCache;
    private final AdminDataCacheImpl<Long, Boolean> adminAccountDataCache;
    private final AdminAccountImpl adminAccount;

    @Override
    public String getCommandSyntax() {
        return ADMIN.getSyntax();
    }

    @Override
    public String execute(Update update, Long chatId) {
        String[] password = update.getMessage().getText().trim().split("\\s");
        String reply;

        if (password.length == 1) {
            adminAccountDataCache.addIfAbsent(chatId, adminAccount.registration(password[0], chatId));
            reply = "Успешно ✅";
        } else {
            reply = "Некорректный пароль ❌";
        }

        dataCache.updateIfPresent(chatId, START);
        log.info("Status {}, Command {}, reply message {}",
                dataCache.get(chatId), this.getCommandSyntax(), reply);
        return reply;
    }

    @Override
    public String waitExecute(Update update, Long chatId) {
        dataCache.updateIfPresent(chatId, ADMIN);
        return "Введите пароль:\n";
    }
}
