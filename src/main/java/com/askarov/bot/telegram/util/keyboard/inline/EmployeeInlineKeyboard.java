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
public class EmployeeInlineKeyboard {

    @Getter
    private final InlineKeyboardMarkup inlineKeyboardMarkup;

    public EmployeeInlineKeyboard() {
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(initEmployeeInlineKeyboard());
    }

    private List<List<InlineKeyboardButton>> initEmployeeInlineKeyboard() {
        List<List<InlineKeyboardButton>> EmployeeMenuList = new ArrayList<>();

        EmployeeMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.EMPLOYEE_CREATE.getInlineName(),
                CallbackDataAndBotState.EMPLOYEE_CREATE.getCommandName()));

        EmployeeMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.EMPLOYEE_UPDATE.getInlineName(),
                CallbackDataAndBotState.EMPLOYEE_UPDATE.getCommandName()));

        EmployeeMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.EMPLOYEE_DELETE.getInlineName(),
                CallbackDataAndBotState.EMPLOYEE_DELETE.getCommandName()));

        return EmployeeMenuList;
    }
}
