package com.askarov.bot.telegram.services.handler.impl;

import com.askarov.bot.telegram.config.BotConfig;
import com.askarov.bot.telegram.services.command.CommandConsole;
import com.askarov.bot.telegram.services.handler.HandlerUpdate;
import com.askarov.bot.telegram.services.command.impl.*;
import com.askarov.bot.telegram.enums.MainMenu;
import com.askarov.bot.telegram.util.keyboard.ReplyKeyboard;
import com.askarov.bot.telegram.util.menu.MenuNavigation;
import com.askarov.bot.telegram.util.menu.impl.EmployeeMenuNavigationImpl;
import com.askarov.bot.telegram.util.menu.impl.ProjectMenuNavigationImpl;
import com.askarov.bot.telegram.util.menu.impl.SearchMenuNavigationImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Slf4j
@Component
//TODO: Временная реализация, подлежит рефакторингу
public class HandlerUpdateImpl implements HandlerUpdate {

    private final List<MenuNavigation> MenuList;
    private final List<CommandConsole> commandList;

    private EmployeeMenuNavigationImpl employeeMenuNavigation;
    private ProjectMenuNavigationImpl projectMenuNavigation;
    private SearchMenuNavigationImpl searchMenuNavigation;

    private CreateCommandImpl createCommandImpl;
    private DeleteCommandImpl deleteCommandImpl;
    private UpdateCommandImpl updateCommandImpl;

    private InfoCommandImpl infoCommand = new InfoCommandImpl();
    private StartCommandImpl startCommand = new StartCommandImpl();

    @Autowired
    public HandlerUpdateImpl(EmployeeMenuNavigationImpl employeeMenuNavigation,
                             ProjectMenuNavigationImpl projectMenuNavigation,
                             SearchMenuNavigationImpl searchMenuNavigation,
                             CreateCommandImpl createCommandImpl,
                             DeleteCommandImpl deleteCommandImpl,
                             UpdateCommandImpl updateCommandImpl) {
        this.employeeMenuNavigation = employeeMenuNavigation;
        this.projectMenuNavigation = projectMenuNavigation;
        this.searchMenuNavigation = searchMenuNavigation;
        this.createCommandImpl = createCommandImpl;
        this.deleteCommandImpl = deleteCommandImpl;
        this.updateCommandImpl = updateCommandImpl;

        MenuList = List.of(
                employeeMenuNavigation,
                projectMenuNavigation,
                searchMenuNavigation
        );

        commandList = List.of(
                createCommandImpl,
                deleteCommandImpl,
                updateCommandImpl
        );
    }

    @Override
    public void updateHandler(BotConfig bot, Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            log.info(message.toString());

            Long chatId = message.getChatId();
            String msg = message.getText();

            outMessageExecute(bot, outMessageMainMenu(update, msg, chatId));

        } else if (update.hasCallbackQuery()) {
            log.info("Bot has callback query: " + update.getCallbackQuery().getData());
            System.out.println(update.getCallbackQuery());

            Long chatId = update.getCallbackQuery().getFrom().getId();
            String msg = update.getCallbackQuery().getData();

            outMessageExecute(bot, outMessageButton(update, msg, chatId));
        }
    }

    private SendMessage outMessageMainMenu(Update update, String msg, Long chatId) {

        SendMessage outMsg = new SendMessage();
        outMsg.setParseMode(ParseMode.HTML);
        outMsg.setChatId(chatId);
        outMsg.setReplyMarkup(ReplyKeyboard.getReplyKeyboardMarkup());

        if (MainMenu.INFO.getMenu().equals(msg)) {
            outMsg.setText(infoCommand.execute(update));
            return outMsg;
        } else if ("/start".equals(msg)) {
            outMsg.setText(startCommand.execute(update));
            return outMsg;
        } else {
            for (MenuNavigation menu : MenuList) {
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

    public SendMessage outMessageButton(Update update, String msg, Long chatId) {

        SendMessage outMsg = new SendMessage();
        outMsg.setParseMode(ParseMode.HTML);
        outMsg.setChatId(chatId);
        outMsg.setReplyMarkup(ReplyKeyboard.getReplyKeyboardMarkup());

        for (CommandConsole command : commandList) {
            if (command.getCommandSyntax().equals(msg.trim())) {
                outMsg.setText(command.execute(update));
                return outMsg;
            }
        }

        outMsg.setText("Я не понимаю, что ты хочешь от меня");
        return outMsg;
    }

    public void outMessageExecute(BotConfig bot, SendMessage outMsg) {
        try {
            bot.execute(outMsg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
