package com.askarov.bot.telegram.services.handler.query.impl;

import com.askarov.bot.telegram.services.command.CommandConsole;
import com.askarov.bot.telegram.services.handler.command.impl.CommandHandlerImpl;
import com.askarov.bot.telegram.services.handler.query.QueryHandler;
import com.askarov.bot.telegram.util.keyboard.ReplyKeyboard;
import lombok.AllArgsConstructor;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class QueryHandlerImpl implements QueryHandler {

    private CommandHandlerImpl commandHandlerImpl;

    @Override
    public SendMessage outMessageButton(Update update, String msg, Long chatId) {
        SendMessage outMsg = new SendMessage();
        outMsg.setParseMode(ParseMode.HTML);
        outMsg.setChatId(chatId);
        outMsg.setReplyMarkup(ReplyKeyboard.getReplyKeyboardMarkup());

        for (CommandConsole command : commandHandlerImpl.getCommandList()) {
            if (command.getCommandSyntax().equals(msg.trim())) {
                outMsg.setText(command.execute(update));
                return outMsg;
            }
        }
        outMsg.setText("Я не понимаю, что ты хочешь от меня");
        return outMsg;
    }
}
