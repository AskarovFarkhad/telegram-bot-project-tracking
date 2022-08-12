package com.askarov.bot.telegram.services.menu.impl;

import com.askarov.bot.telegram.services.menu.keyboard.inline.SearchInlineKeyboard;
import com.askarov.bot.telegram.services.enums.MainMenu;
import com.askarov.bot.telegram.services.menu.MenuNavigation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * @author Farkhad Askarov
 */
@Component
public class SearchMenuNavigationImpl implements MenuNavigation {

    private final SearchInlineKeyboard searchInlineKeyboard;

    @Autowired
    public SearchMenuNavigationImpl(SearchInlineKeyboard searchInlineKeyboard) {
        this.searchInlineKeyboard = searchInlineKeyboard;
    }

    @Override
    public String getMenuSyntax() {
        return MainMenu.SEARCH.getMenu();
    }

    @Override
    public InlineKeyboardMarkup execute() {
        return searchInlineKeyboard.getInlineKeyboardMarkup();
    }
}
