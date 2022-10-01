package com.askarov.bot.telegram;

import com.askarov.bot.telegram.config.BotConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.generics.BotSession;

import java.util.Optional;

@SpringBootTest
class ApplicationTests {

	private final BotConfig bot;

	@Autowired
	ApplicationTests(BotConfig bot) {
		this.bot = bot;
	}

	@Test
	void hasBotSessionAndBotRunning() {
		Optional<BotSession> botSession = Application.botConnect(bot);
		Assertions.assertTrue(botSession.isPresent());
		Assertions.assertTrue(botSession.get().isRunning());
	}
}
