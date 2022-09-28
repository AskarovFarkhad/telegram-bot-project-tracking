package com.askarov.bot.telegram.util.keyboard.inline;

import com.askarov.bot.telegram.enums.CallbackData;
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
                CallbackData.SEARCH_EMPLOYEE.getInlineName(),
                CallbackData.SEARCH_EMPLOYEE.getCommandName()));

        SearchMenuList.add(InlineKeyboard.getButton(
                CallbackData.SEARCH_PROJECT.getInlineName(),
                CallbackData.SEARCH_PROJECT.getCommandName()));

        SearchMenuList.add(InlineKeyboard.getButton(
                CallbackData.SEARCH_ALL_EMPLOYEES.getInlineName(),
                CallbackData.SEARCH_ALL_EMPLOYEES.getCommandName()));

        SearchMenuList.add(InlineKeyboard.getButton(
                CallbackData.CANCEL.getInlineName(),
                CallbackData.CANCEL.getCommandName()));

        return SearchMenuList;
    }
}
