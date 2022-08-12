package com.askarov.bot.telegram.services.menu;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * @author Farkhad Askarov
 */
public interface MenuNavigation {

    String getMenuSyntax();

    InlineKeyboardMarkup execute();
}
