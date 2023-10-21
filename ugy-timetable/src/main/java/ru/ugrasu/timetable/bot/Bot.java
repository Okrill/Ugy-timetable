package ru.ugrasu.timetable.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;

import lombok.extern.slf4j.Slf4j;
import ru.ugrasu.timetable.router.CallbackRouter;
import ru.ugrasu.timetable.router.MessageRouter;
import ru.ugrasu.timetable.templates.AnswerTemplate;

/*
 * Bot that route updates
 */
@Component
@Slf4j
public class Bot extends BotProperties {

	@Autowired
	private CallbackRouter callbackRouter;

	@Autowired
	private MessageRouter messageRouter;

	@Autowired
	private AnswerTemplate defaultAnswerSender;

	public Bot(SetWebhook setWebhook) {
		super(setWebhook);
	}

	@Override
	public BotApiMethod<?> onWebhookUpdateReceived(Update update) {

		try {
			return routeUpdate(update);
		} catch (Exception e) {
			log.error("ERROR:", e);
			return null;
		}
	}

	private BotApiMethod<?> routeUpdate(Update update) {

		if (update.hasCallbackQuery()) {
			return callbackRouter.callbackRoute(update);
		} else if (update.hasMessage()) {
			return messageRouter.messageRoute(update);
		}
		return defaultAnswerSender.sendMessage(update.getMessage(), "/help - список команд");
	}
}
