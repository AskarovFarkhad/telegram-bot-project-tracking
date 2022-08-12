package com.askarov.bot.telegram.services.menu.keyboard.inline;

import com.askarov.bot.telegram.services.menu.keyboard.InlineKeyboard;
import com.askarov.bot.telegram.services.enums.CallbackData;
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
                CallbackData.EMPLOYEE_CREATE.getInlineName(),
                CallbackData.EMPLOYEE_CREATE.getCallbackData()));

        EmployeeMenuList.add(InlineKeyboard.getButton(
                CallbackData.EMPLOYEE_UPDATE.getInlineName(),
                CallbackData.EMPLOYEE_UPDATE.getCallbackData()));

        EmployeeMenuList.add(InlineKeyboard.getButton(
                CallbackData.EMPLOYEE_DELETE.getInlineName(),
                CallbackData.EMPLOYEE_DELETE.getCallbackData()));

        return EmployeeMenuList;
    }
}
