package com.askarov.bot.telegram.services.handler.msg.impl;

import com.askarov.bot.telegram.enums.CallbackData;
import com.askarov.bot.telegram.services.CommandContainer;
import com.askarov.bot.telegram.services.handler.msg.MsgHandler;
import com.askarov.bot.telegram.statecontroller.EmployeeDataCache;
import com.askarov.bot.telegram.util.keyboard.ReplyKeyboard;
import com.askarov.bot.telegram.util.menu.InfoMenuNavigation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.MainMenu.INFO;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MsgHandlerImpl implements MsgHandler {

    private static final String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;
    private final InfoMenuNavigation infoCommand;
    private final EmployeeDataCache<Long, CallbackData> employeeDataCache;

    @Override
    public SendMessage outMessageText(Update update, String text, Long chatId) {
        SendMessage outMsg = new SendMessage();
        outMsg.setParseMode(ParseMode.HTML);
        outMsg.setChatId(chatId);
        outMsg.setReplyMarkup(ReplyKeyboard.getReplyKeyboardMarkup());

        if (text.equals("/start")) {
            employeeDataCache.addIfAbsent(chatId, CallbackData.START);
            outMsg.setText(commandContainer.retrieveCommand("/start").execute(update));
        } else if (INFO.getMenu().equals(text)) {
            outMsg.setText(infoCommand.execute(update));
        } else if (commandContainer.getMenuMap().containsKey(text)) {
            outMsg.setText("Выберите действие: ");
            outMsg.setReplyMarkup(commandContainer.retrieveMenu(text).execute());
        } else if (text.startsWith(COMMAND_PREFIX)) {
            outMsg.setText(commandContainer.retrieveCommand(text.trim()).waitExecute(update));
        } else {
            CallbackData botState = employeeDataCache.get(chatId);
            outMsg.setText(commandContainer.retrieveCommand(botState.getCommandName()).execute(update));
        }
        return outMsg;
    }
}
