package com.askarov.bot.telegram.services.admin.impl;

import com.askarov.bot.telegram.services.admin.AdminAccount;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
@Service
@PropertySource("classpath:bot.properties")
public class AdminAccountImpl implements AdminAccount {

    @Value("${admin.password}")
    private String ADMIN_PASSWORD;
    private boolean result;

    @Override
    public boolean registration(String password, Long chatId) {
        result = password != null && password.equals(ADMIN_PASSWORD);
        log.info("Admin {}, registration {}", chatId, result);
        return result;
    }
}
