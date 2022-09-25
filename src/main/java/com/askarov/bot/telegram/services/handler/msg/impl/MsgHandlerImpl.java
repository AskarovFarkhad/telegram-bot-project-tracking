package com.askarov.bot.telegram.services.handler.msg.impl;

import com.askarov.bot.telegram.services.command.impl.InfoCommandImpl;
import com.askarov.bot.telegram.services.CommandContainer;
import com.askarov.bot.telegram.services.handler.msg.MsgHandler;
import com.askarov.bot.telegram.util.keyboard.ReplyKeyboard;
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
    private final InfoCommandImpl infoCommand;

    @Override
    public SendMessage outMessageText(Update update, String msg, Long chatId) {
        SendMessage outMsg = new SendMessage();
        outMsg.setParseMode(ParseMode.HTML);
        outMsg.setChatId(chatId);
        outMsg.setReplyMarkup(ReplyKeyboard.getReplyKeyboardMarkup());

        if (INFO.getMenu().equals(msg)) {
            outMsg.setText(infoCommand.execute(update));
        } else if (commandContainer.getMenuMap().containsKey(msg)) {
            outMsg.setText("Выберите действие: ");
            outMsg.setReplyMarkup(commandContainer.retrieveMenu(msg).execute());
        } else if (msg.startsWith(COMMAND_PREFIX)) {
            outMsg.setText(commandContainer.retrieveCommand(msg.trim().toLowerCase()).execute(update));
        } else {
            outMsg.setText(commandContainer.retrieveCommand(null).execute(update));
        }
        return outMsg;
    }
}
