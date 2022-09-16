package com.askarov.bot.telegram.util.keyboard;

import com.askarov.bot.telegram.enums.MainMenu;
import lombok.Getter;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

/**
 * @author Farkhad Askarov
 */
@Component
public class ReplyKeyboard {

    @Getter
    private static ReplyKeyboardMarkup replyKeyboardMarkup;

    /**
     * Initialization Keyboard
     */
    public static void initReplyKeyboard() {
        ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();
        keyboardRows.add(keyboardFirstRow);
        keyboardFirstRow.add(new KeyboardButton(MainMenu.EMPLOYEE.getMenu()));
        keyboardFirstRow.add(new KeyboardButton(MainMenu.PROJECT.getMenu()));

        KeyboardRow keyboardSecondRow = new KeyboardRow();
        keyboardRows.add(keyboardSecondRow);
        keyboardSecondRow.add(new KeyboardButton(MainMenu.SEARCH.getMenu()));
        keyboardSecondRow.add(new KeyboardButton(MainMenu.INFO.getMenu()));

        replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
    }
}
