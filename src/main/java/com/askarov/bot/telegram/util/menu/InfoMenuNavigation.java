package com.askarov.bot.telegram.util.menu;

import com.askarov.bot.telegram.enums.MainMenu;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * @author Farkhad Askarov
 */
@Service
public class InfoMenuNavigation {

    private final static String INFO_MESSAGE = ", \n\n" +
            "В меню <b><i>" + MainMenu.EMPLOYEE.getMenu() + "</i></b> можно управлять своими данными.\n\n" +
            "В меню <b><i>" + MainMenu.PROJECT.getMenu() + "</i></b> можно управлять проектами.\n\n" +
            "В меню <b><i>" + MainMenu.SEARCH.getMenu() + "</i></b> можно найти своего коллегу или же проект.\n\n";

    public String getCommandSyntax() {
        return MainMenu.INFO.getMenu();
    }

    public String execute(Update update) {
        return update.getMessage().getFrom().getFirstName() + INFO_MESSAGE;
    }

}
