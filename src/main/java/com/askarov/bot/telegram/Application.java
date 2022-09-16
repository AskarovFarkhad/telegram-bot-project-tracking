package com.askarov.bot.telegram;

import com.askarov.bot.telegram.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Slf4j
@SpringBootApplication
public class Application {

	final static int RECONNECT_PAUSE = 10_000;

	private static BotConfig bot;

	@Autowired
	public Application(BotConfig bot) {
		Application.bot = bot;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		botConnect(bot);
	}

	public static void botConnect(BotConfig bot) {
		try {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(bot);
			log.info("registerBot() --> Bot is registered successfully");
		} catch (TelegramApiException e) {
			e.printStackTrace();
			log.error("registerBot() --> Can't connect to Bot" +
					" Pause " + RECONNECT_PAUSE / 1000 + " sec and try again. Error: " + e.getMessage());
			try {
				Thread.sleep(RECONNECT_PAUSE);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}
