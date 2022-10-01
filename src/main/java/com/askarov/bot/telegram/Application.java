package com.askarov.bot.telegram;

import com.askarov.bot.telegram.config.BotConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.BotSession;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Optional;

@Slf4j
@SpringBootApplication
public class Application {

	static final int RECONNECT_PAUSE = 10_000;
	private static BotConfig bot;

	@Autowired
	public Application(BotConfig bot) {
		Application.bot = bot;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		botConnect(bot);
	}

	public static Optional<BotSession> botConnect(BotConfig bot) {
		try {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			Optional<BotSession> optionalBotSession = Optional.of(telegramBotsApi.registerBot(bot));
			log.info("Bot is registered successfully");
			return optionalBotSession;
		} catch (TelegramApiException telegramApiException) {
			log.error("Can't connect to Bot. Pause {} sec and try again. Error: {}",
					RECONNECT_PAUSE / 1000, telegramApiException.getMessage());
			try {
				Thread.sleep(RECONNECT_PAUSE);
			} catch (InterruptedException interruptedException) {
				log.error("Error: {}", interruptedException.getMessage());
			}
		}
		return Optional.empty();
	}
}
