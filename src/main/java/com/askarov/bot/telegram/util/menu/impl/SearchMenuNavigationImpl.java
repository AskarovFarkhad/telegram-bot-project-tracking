package com.askarov.bot.telegram.util.menu.impl;

import com.askarov.bot.telegram.enums.MainMenu;
import com.askarov.bot.telegram.util.keyboard.inline.SearchInlineKeyboard;
import com.askarov.bot.telegram.util.menu.MenuNavigation;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

/**
 * @author Farkhad Askarov
 */
@Component
public class SearchMenuNavigationImpl implements MenuNavigation {

    private final SearchInlineKeyboard searchInlineKeyboard;

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
