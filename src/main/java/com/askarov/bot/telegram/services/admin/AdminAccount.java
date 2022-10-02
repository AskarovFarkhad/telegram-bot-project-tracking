package com.askarov.bot.telegram.services.admin;

public interface AdminAccount {

    boolean registration(String password, Long chatId);
}
