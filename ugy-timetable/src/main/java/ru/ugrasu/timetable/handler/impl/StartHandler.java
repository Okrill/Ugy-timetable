package ru.ugrasu.timetable.handler.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import ru.ugrasu.timetable.bpp.annotation.CommandHandlerMethod;
import ru.ugrasu.timetable.commands.Command;
import ru.ugrasu.timetable.handler.CommandHandler;
import ru.ugrasu.timetable.templates.AnswerTemplate;

@Component
public class StartHandler implements CommandHandler {

	@Autowired
	private AnswerTemplate answerTemplate;
	/**
	 * Handler for {@link Command.START}
	 */
	@CommandHandlerMethod(Command.START)
	private SendMessage handleStart(Message message) {
		List<List<InlineKeyboardButton>> buttons = List.of(List.of(InlineKeyboardButton.builder()
			.text("Выбрать институт")
			.callbackData(Command.FACULTIES.getName())
			.build()));
		return answerTemplate.sendInlineKeyboardButtons(message, "Расписание", buttons);
	}
}
