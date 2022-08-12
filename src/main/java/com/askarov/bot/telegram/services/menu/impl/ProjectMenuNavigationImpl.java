package com.askarov.bot.telegram.services.menu.impl;

import com.askarov.bot.telegram.services.menu.keyboard.inline.ProjectInlineKeyboard;
import com.askarov.bot.telegram.services.enums.MainMenu;
import com.askarov.bot.telegram.services.menu.MenuNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * @author Farkhad Askarov
 */
@Component
public class ProjectMenuNavigationImpl implements MenuNavigation {

    private final ProjectInlineKeyboard projectInlineKeyboard;

    @Autowired
    public ProjectMenuNavigationImpl(ProjectInlineKeyboard projectInlineKeyboard) {
        this.projectInlineKeyboard = projectInlineKeyboard;
    }

    @Override
    public String getMenuSyntax() {
        return MainMenu.PROJECT.getMenu();
    }

    @Override
    public InlineKeyboardMarkup execute() {
        return projectInlineKeyboard.getInlineKeyboardMarkup();
    }
}
