package com.askarov.bot.telegram.services;

import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.repository.EmployeeRepository;
import com.askarov.bot.telegram.repository.ProjectRepository;
import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.services.command.impl.CancelCommandImpl;
import com.askarov.bot.telegram.services.command.impl.StartCommandImpl;
import com.askarov.bot.telegram.services.command.impl.UnknownCommandImpl;
import com.askarov.bot.telegram.services.command.impl.employee.*;
import com.askarov.bot.telegram.statecontroller.BotState;
import com.askarov.bot.telegram.statecontroller.EmployeeDataCache;
import com.askarov.bot.telegram.util.keyboard.inline.EmployeeInlineKeyboard;
import com.askarov.bot.telegram.util.keyboard.inline.ProjectInlineKeyboard;
import com.askarov.bot.telegram.util.keyboard.inline.SearchInlineKeyboard;
import com.askarov.bot.telegram.util.menu.MenuNavigation;
import com.askarov.bot.telegram.util.menu.impl.EmployeeMenuNavigationImpl;
import com.askarov.bot.telegram.util.menu.impl.ProjectMenuNavigationImpl;
import com.askarov.bot.telegram.util.menu.impl.SearchMenuNavigationImpl;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.askarov.bot.telegram.enums.CallbackData.*;
import static com.askarov.bot.telegram.enums.MainMenu.*;

@Getter
@Service
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final ImmutableMap<String, MenuNavigation> menuMap;

    private final UnknownCommandImpl unknownCommand;

    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    private final EmployeeDataCache<Long, CallbackData> employeeDataCache;

    private final EmployeeInlineKeyboard employeeInlineKeyboard;
    private final ProjectInlineKeyboard projectInlineKeyboard;
    private final SearchInlineKeyboard searchInlineKeyboard;

    @Autowired
    public CommandContainer(UnknownCommandImpl unknownCommand,
                            EmployeeRepository employeeRepository,
                            ProjectRepository projectRepository,
                            EmployeeDataCache<Long, CallbackData> employeeDataCache,
                            EmployeeInlineKeyboard employeeInlineKeyboard,
                            ProjectInlineKeyboard projectInlineKeyboard,
                            SearchInlineKeyboard searchInlineKeyboard) {

        this.unknownCommand = unknownCommand;
        this.employeeDataCache = employeeDataCache;
        this.employeeInlineKeyboard = employeeInlineKeyboard;
        this.projectInlineKeyboard = projectInlineKeyboard;
        this.searchInlineKeyboard = searchInlineKeyboard;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommandImpl())
                .put(EMPLOYEE_CREATE.getCommandName(), new CreateEmployeeCommandImpl(employeeRepository, employeeDataCache))
                .put(EMPLOYEE_DELETE.getCommandName(), new DeleteEmployeeCommandImpl(employeeRepository))
                .put(EMPLOYEE_UPDATE.getCommandName(), new UpdateEmployeeCommandImpl(employeeRepository))
                .put(CANCEL.getCommandName(), new CancelCommandImpl())
                .build();

        menuMap = ImmutableMap.<String, MenuNavigation>builder()
                .put(EMPLOYEE.getMenu(), new EmployeeMenuNavigationImpl(employeeInlineKeyboard))
                .put(PROJECT.getMenu(), new ProjectMenuNavigationImpl(projectInlineKeyboard))
                .put(SEARCH.getMenu(), new SearchMenuNavigationImpl(searchInlineKeyboard))
                .build();
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }

    public MenuNavigation retrieveMenu(String menuIdentifier) {
        return menuMap.get(menuIdentifier);
    }
}
