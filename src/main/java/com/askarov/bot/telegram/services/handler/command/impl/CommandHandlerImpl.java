package com.askarov.bot.telegram.services.handler.command.impl;

import com.askarov.bot.telegram.services.command.CommandConsole;
import com.askarov.bot.telegram.services.command.impl.*;
import com.askarov.bot.telegram.services.handler.command.CommandHandler;
import com.askarov.bot.telegram.util.menu.MenuNavigation;
import com.askarov.bot.telegram.util.menu.impl.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class CommandHandlerImpl implements CommandHandler {

    private EmployeeMenuNavigationImpl employeeMenuNavigation;
    private ProjectMenuNavigationImpl projectMenuNavigation;
    private SearchMenuNavigationImpl searchMenuNavigation;

    private List<MenuNavigation> menuList;

    private CreateCommandImpl createCommandImpl;
    private DeleteCommandImpl deleteCommandImpl;
    private InfoCommandImpl infoCommand;
    private StartCommandImpl startCommand;
    private UpdateCommandImpl updateCommandImpl;

    private List<CommandConsole> commandList;

    {
        menuList.add(employeeMenuNavigation);
        menuList.add(projectMenuNavigation);
        menuList.add(searchMenuNavigation);

        commandList.add(createCommandImpl);
        commandList.add(deleteCommandImpl);
        commandList.add(infoCommand);
        commandList.add(startCommand);
        commandList.add(updateCommandImpl);
    }
}
