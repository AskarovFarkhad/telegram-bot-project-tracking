package com.askarov.bot.telegram.services.menu.impl;

import com.askarov.bot.telegram.services.menu.keyboard.inline.EmployeeInlineKeyboard;
import com.askarov.bot.telegram.services.enums.MainMenu;
import com.askarov.bot.telegram.services.menu.MenuNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * @author Farkhad Askarov
 */
@Component
public class EmployeeMenuNavigationImpl implements MenuNavigation {

    private final EmployeeInlineKeyboard employeeInlineKeyboard;

    @Autowired
    public EmployeeMenuNavigationImpl(EmployeeInlineKeyboard employeeInlineKeyboard) {
        this.employeeInlineKeyboard = employeeInlineKeyboard;
    }

    @Override
    public String getMenuSyntax() {
        return MainMenu.EMPLOYEE.getMenu();
    }

    @Override
    public InlineKeyboardMarkup execute() {
        return employeeInlineKeyboard.getInlineKeyboardMarkup();
    }
}
