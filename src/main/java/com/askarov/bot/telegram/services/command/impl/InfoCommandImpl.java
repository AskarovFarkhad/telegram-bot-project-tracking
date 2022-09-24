package com.askarov.bot.telegram.services.command.impl;

import com.askarov.bot.telegram.services.command.CommandConsole;
import com.askarov.bot.telegram.enums.MainMenu;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Farkhad Askarov
 */
@Service
public class InfoCommandImpl implements CommandConsole {

    @Override
    public String getCommandSyntax() {
        return MainMenu.INFO.getMenu();
    }

    @Override
    public String execute(Update update) {
        return  update.getMessage().getFrom().getFirstName() + ", \n\n" +
                "В меню <b><i>" + MainMenu.EMPLOYEE.getMenu() + "</i></b> можно управлять своими данными.\n\n" +
                "В меню <b><i>" + MainMenu.PROJECT.getMenu() + "</i></b> можно управлять проектами.\n\n" +
                "В меню <b><i>" + MainMenu.SEARCH.getMenu() + "</i></b> можно найти своего коллегу или же проект.\n\n";
    }
}
