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
            "В меню <b><i>" + MainMenu.EMPLOYEE.getMenu() + "</i></b> можно:\n\n" +
            "/create - добавить себя с список;\n" +
            "/update - обновить свои данные;\n" +
            "/delete - удалить сотрудника (доступно для /admin); \n\n" +
            "В меню <b><i>" + MainMenu.PROJECT.getMenu() + "</i></b> можно:\n\n" +
            "/project - добавить проект;\n" +
            "/update_pr - обновить название проекта по номеру;\n" +
            "/delete_pr - удалить проект (доступно для /admin);\n\n" +
            "В меню <b><i>" + MainMenu.SEARCH.getMenu() + "</i></b> можно:\n\n" +
            "/search_emp - найти сотрудника по фамилии;\n" +
            "/search_pr - найти проект по номеру;\n" +
            "/list - вывести всех сотрудников с историей проектов;\n\n";

    public String getCommandSyntax() {
        return MainMenu.INFO.getMenu();
    }

    public String execute(Update update) {
        return update.getMessage().getFrom().getFirstName() + INFO_MESSAGE;
    }
}
