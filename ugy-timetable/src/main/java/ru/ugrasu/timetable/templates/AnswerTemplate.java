package ru.ugrasu.timetable.templates;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import ru.ugrasu.timetable.commands.Command;
import ru.ugrasu.timetable.model.Lesson;

@Component
public class AnswerTemplate {

	private List<List<InlineKeyboardButton>> getLessonsButtons(int groupOid) {
		return List.of(List.of(InlineKeyboardButton.builder()
			.text("Следущая неделя")
			.callbackData(Command.LESSONS_ON_NEXT_WEEK.getName() +
				"," + groupOid)
			.build(), InlineKeyboardButton.builder()
				.text("Текущая неделя")
				.callbackData(Command.LESSONS_ON_THIS_WEEK.getName() +
					"," + groupOid)
				.build()), getButtonWithMainLink());
	}

	private List<InlineKeyboardButton> getButtonWithMainLink() {
		return List.of(InlineKeyboardButton.builder()
			.text("На главную")
			.callbackData(Command.FACULTIES.getName())
			.build());
	}

	public SendMessage sendMessage(Message message, String textMessage) {
		return SendMessage.builder()
			.text(textMessage)
			.chatId(message.getChatId())
			.build();
	}

	public SendMessage sendLessons(Message message, List<Lesson> lessons, int groupOid) {
		StringBuilder sb = new StringBuilder();
		Set<Integer> dayOfWeekSet = new HashSet<>();
		lessons.forEach(lesson -> {

			if (!dayOfWeekSet.contains(lesson.getDayOfWeek())) {
				sb.append(lesson.getDayOfWeekString() + "\n➖➖➖\n");
				dayOfWeekSet.add(lesson.getDayOfWeek());
			}
			sb.append(lesson + "\n");
		});
		return SendMessage.builder()
			.text(sb.toString())
			.chatId(message.getChatId())
			.replyMarkup(InlineKeyboardMarkup.builder().keyboard(getLessonsButtons(groupOid)).build())
			.build();
	}

	public SendMessage sendInlineKeyboardButtons(Message message, String textMessage,
		List<List<InlineKeyboardButton>> buttons) {
		return SendMessage.builder()
			.text(textMessage)
			.chatId(message.getChatId())
			.replyMarkup(InlineKeyboardMarkup.builder().keyboard(buttons).build())
			.build();
	}
}
