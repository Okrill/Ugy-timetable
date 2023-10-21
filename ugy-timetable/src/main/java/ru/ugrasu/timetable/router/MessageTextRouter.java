package ru.ugrasu.timetable.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import ru.ugrasu.timetable.commands.Command;
import ru.ugrasu.timetable.handler.CommandHandlerFactory;
import ru.ugrasu.timetable.util.TextValidate;

@Component
public class MessageTextRouter {

	@Autowired
	private CommandHandlerFactory commandHandlerFactory;

	@Autowired
	private TextValidate textValidate;

	public BotApiMethod<?> textRoute(Message message) {
		String inputText = message.getText();

		if (textValidate.isCommand(inputText)) {
			return commandHandlerFactory.handleCommand(message, inputText, true);
		} else {
			return commandHandlerFactory.handleCommand(message, Command.ERROR);
		}
	}
}
