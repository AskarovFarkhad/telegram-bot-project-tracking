package com.askarov.bot.telegram.util.menu;

import com.askarov.bot.telegram.enums.MainMenu;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.*;

/**
 * @author Farkhad Askarov
 */
@Service
public class InfoMenuNavigation {

    private final static String INFO_MESSAGE = ", \n\n" +
            "В меню <b><i>" + MainMenu.EMPLOYEE.getMenu() + "</i></b> можно:\n\n" +
            EMPLOYEE_CREATE.getSyntax() + " - добавить себя с список;\n" +
            EMPLOYEE_UPDATE.getSyntax() + " - обновить свои данные;\n" +
            EMPLOYEE_DELETE.getSyntax() + " - удалить сотрудника" +
            "(доступно для " + ADMIN.getSyntax() + ");\n\n" +

            "В меню <b><i>" + MainMenu.PROJECT.getMenu() + "</i></b> можно:\n\n" +
            PROJECT_ADD.getSyntax() + " - добавить проект;\n" +
            PROJECT_UPDATE.getSyntax() + " - обновить название проекта по номеру;\n" +
            PROJECT_DELETE.getSyntax() + " - удалить проект\n" +
            "(доступно для " + ADMIN.getSyntax() + ");\n\n" +

            "В меню <b><i>" + MainMenu.SEARCH.getMenu() + "</i></b> можно:\n\n" +
            SEARCH_EMPLOYEE.getSyntax() + " - найти сотрудника по фамилии;\n" +
            SEARCH_PROJECT.getSyntax() + " - найти проект по номеру;\n" +
            SEARCH_ALL_EMPLOYEES.getSyntax() + " - вывести всех сотрудников с историей проектов;\n\n";

    public String getCommandSyntax() {
        return MainMenu.INFO.getMenu();
    }

    public String execute(Update update) {
        return update.getMessage().getFrom().getFirstName() + INFO_MESSAGE;
    }
}
