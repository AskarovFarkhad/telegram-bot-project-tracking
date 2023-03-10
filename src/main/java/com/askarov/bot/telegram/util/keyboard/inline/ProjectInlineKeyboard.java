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
public class ProjectInlineKeyboard {

    @Getter
    private final InlineKeyboardMarkup inlineKeyboardMarkup;

    public ProjectInlineKeyboard() {
        inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(initEmployeeInlineKeyboard());
    }

    private List<List<InlineKeyboardButton>> initEmployeeInlineKeyboard() {
        List<List<InlineKeyboardButton>> ProjectMenuList = new ArrayList<>();

        ProjectMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.PROJECT_ADD.getCommandName(),
                CallbackDataAndBotState.PROJECT_ADD.getSyntax()));

        ProjectMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.PROJECT_UPDATE.getCommandName(),
                CallbackDataAndBotState.PROJECT_UPDATE.getSyntax()));

        ProjectMenuList.add(InlineKeyboard.getButton(
                CallbackDataAndBotState.PROJECT_DELETE.getCommandName(),
                CallbackDataAndBotState.PROJECT_DELETE.getSyntax()));

        return ProjectMenuList;
    }
}
