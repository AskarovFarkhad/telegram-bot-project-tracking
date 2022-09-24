package com.askarov.bot.telegram.services.handler.msg.impl;

import com.askarov.bot.telegram.enums.MainMenu;
import com.askarov.bot.telegram.services.handler.command.impl.CommandHandlerImpl;
import com.askarov.bot.telegram.services.handler.msg.MsgHandler;
import com.askarov.bot.telegram.util.keyboard.ReplyKeyboard;
import com.askarov.bot.telegram.util.menu.MenuNavigation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Service
@AllArgsConstructor(onConstructor_ = {@Autowired})
public class MsgHandlerImpl implements MsgHandler {

    private CommandHandlerImpl commandHandlerImpl;

    @Override
    public SendMessage outMessageText(Update update, String msg, Long chatId) {
        SendMessage outMsg = new SendMessage();
        outMsg.setParseMode(ParseMode.HTML);
        outMsg.setChatId(chatId);
        outMsg.setReplyMarkup(ReplyKeyboard.getReplyKeyboardMarkup());

        if (MainMenu.INFO.getMenu().equals(msg)) {
            outMsg.setText(commandHandlerImpl.getInfoCommand().execute(update));
            return outMsg;
        } else if ("/start".equals(msg)) {
            outMsg.setText(commandHandlerImpl.getStartCommand().execute(update));
            return outMsg;
        } else {
            for (MenuNavigation menu : commandHandlerImpl.getMenuList()) {
                if (menu.getMenuSyntax().equals(msg)) {
                    InlineKeyboardMarkup inlineKeyboardMarkup = menu.execute();
                    outMsg.setReplyMarkup(inlineKeyboardMarkup);
                    outMsg.setText("Выберите действие: ");
                    return outMsg;
                }
            }
        }
        outMsg.setText("Я не понимаю, что ты хочешь от меня");
        return outMsg;
    }
}
