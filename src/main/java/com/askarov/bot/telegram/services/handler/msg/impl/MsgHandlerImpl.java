package com.askarov.bot.telegram.services.handler.msg.impl;

import com.askarov.bot.telegram.enums.CallbackDataAndBotState;
import com.askarov.bot.telegram.services.CommandContext;
import com.askarov.bot.telegram.services.handler.msg.MsgHandler;
import com.askarov.bot.telegram.cache.EmployeeDataCache;
import com.askarov.bot.telegram.util.keyboard.ReplyKeyboard;
import com.askarov.bot.telegram.util.menu.InfoMenuNavigation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.askarov.bot.telegram.enums.CallbackDataAndBotState.START;
import static com.askarov.bot.telegram.enums.MainMenu.INFO;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MsgHandlerImpl implements MsgHandler {

    private static final String COMMAND_PREFIX = "/";

    private final CommandContext commandContext;
    private final InfoMenuNavigation infoCommand;
    private final EmployeeDataCache<Long, CallbackDataAndBotState> employeeDataCache;

    @Override
    public SendMessage outMessageText(Update update, String text, Long chatId) {
        SendMessage outMsg = new SendMessage();
        outMsg.setParseMode(ParseMode.HTML);
        outMsg.setChatId(chatId);
        outMsg.setReplyMarkup(ReplyKeyboard.getReplyKeyboardMarkup());

        if (text.equals(START.getSyntax())) {
            employeeDataCache.addIfAbsent(chatId, START);
            outMsg.setText(commandContext.retrieveCommand(START.getSyntax()).execute(update, chatId));
        } else if (INFO.getMenu().equals(text)) {
            outMsg.setText(infoCommand.execute(update));
        } else if (commandContext.getMenuMap().containsKey(text)) {
            outMsg.setText("Выберите действие: ");
            outMsg.setReplyMarkup(commandContext.retrieveMenu(text).execute());
        } else if (text.startsWith(COMMAND_PREFIX)) {
            outMsg.setText(commandContext.retrieveCommand(text.trim()).waitExecute(update, chatId));
        } else {
            CallbackDataAndBotState botState = employeeDataCache.get(chatId);
            if (botState != START) {
                outMsg.setText(commandContext.retrieveCommand(botState.getSyntax()).execute(update, chatId));
            } else {
                outMsg.setText(commandContext.retrieveCommand(null).execute(update, chatId));
            }
        }
        return outMsg;
    }
}
