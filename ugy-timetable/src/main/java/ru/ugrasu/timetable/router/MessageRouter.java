package ru.ugrasu.timetable.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import ru.ugrasu.timetable.templates.AnswerTemplate;

@Component
public class MessageRouter {

	@Autowired
	private MessageTextRouter messageTextRouter;

	@Autowired
	private AnswerTemplate defaultAnswerSender;

	public BotApiMethod<?> messageRoute(Update update) {
		Message message = update.getMessage();

		if (message.hasText()) {
			return messageTextRouter.textRoute(message);
		} else
			return defaultAnswerSender.sendMessage(message, "/help - список команд");
	}
}
