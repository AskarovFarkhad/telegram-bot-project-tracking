package com.askarov.bot.telegram.util.keyboard.inline;

import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.util.keyboard.InlineKeyboard;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Farkhad Askarov
 */
@Component
public class SearchInlineKeyboard {

    @Getter
    private final InlineKeyboardMarkup inlineKeyboardMarkup;

    public SearchInlineKeyboard() {
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(initEmployeeInlineKeyboard());
    }

    private List<List<InlineKeyboardButton>> initEmployeeInlineKeyboard() {
        List<List<InlineKeyboardButton>> SearchMenuList = new ArrayList<>();

        SearchMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.SEARCH_EMPLOYEE.getCommandName(),
                CallbackDataAndBotState.SEARCH_EMPLOYEE.getSyntax()));

        SearchMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.SEARCH_PROJECT.getCommandName(),
                CallbackDataAndBotState.SEARCH_PROJECT.getSyntax()));

        SearchMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.SEARCH_ALL_EMPLOYEES.getCommandName(),
                CallbackDataAndBotState.SEARCH_ALL_EMPLOYEES.getSyntax()));

        return SearchMenuList;
    }
}
