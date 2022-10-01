package com.askarov.bot.telegram.services;

import com.askarov.bot.telegram.services.command.Command;
import com.askarov.bot.telegram.services.command.impl.general.UnknownCommandImpl;
import com.askarov.bot.telegram.util.menu.MenuNavigation;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Service
public class CommandContext {

    private final Map<String, Command> commandMap = new HashMap<>();
    private final Map<String, MenuNavigation> menuMap = new HashMap<>();

    @Autowired
    public CommandContext(List<Command> commandMap, List<MenuNavigation> menuMap) {
        commandMap.forEach(command -> this.getCommandMap().put(command.getCommandSyntax(), command));
        menuMap.forEach(menu -> this.getMenuMap().put(menu.getMenuSyntax(), menu));
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, new UnknownCommandImpl());
    }

    public MenuNavigation retrieveMenu(String menuIdentifier) {
        return menuMap.get(menuIdentifier);
    }
}
