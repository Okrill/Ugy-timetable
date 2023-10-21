package ru.ugrasu.timetable.handler.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import ru.ugrasu.timetable.bpp.annotation.CommandHandlerMethod;
import ru.ugrasu.timetable.commands.Command;
import ru.ugrasu.timetable.handler.CommandHandler;
import ru.ugrasu.timetable.templates.AnswerTemplate;

@Component
public class ErrorsHandler implements CommandHandler {

	@Autowired
	private AnswerTemplate answerTemplate;

	/**
	 * Handler for {@link Command.ERROR}
	 */
	@CommandHandlerMethod(Command.ERROR)
	private SendMessage handleError(Message message) {
		return answerTemplate.sendMessage(message, "❌ У данной команды нет поддержки");
	}
}
