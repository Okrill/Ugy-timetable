package ru.ugrasu.timetable.handler.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import ru.ugrasu.timetable.bpp.annotation.CommandHandlerMethod;
import ru.ugrasu.timetable.commands.Command;
import ru.ugrasu.timetable.handler.CommandHandler;
import ru.ugrasu.timetable.model.Faculty;
import ru.ugrasu.timetable.model.Group;
import ru.ugrasu.timetable.model.Lesson;
import ru.ugrasu.timetable.model.TimetableEntity;
import ru.ugrasu.timetable.service.FacultiesService;
import ru.ugrasu.timetable.service.GroupsService;
import ru.ugrasu.timetable.service.LessonsService;
import ru.ugrasu.timetable.templates.AnswerTemplate;

@Component
public class TimetableHandler implements CommandHandler {

	@Autowired
	private AnswerTemplate answerTemplate;

	@Autowired
	private FacultiesService facultiesService;

	@Autowired
	private LessonsService lessonsService;

	@Autowired
	private GroupsService groupsService;

	private LocalDate getStartDayOfWeek(LocalDate now) {
		int indexDayOfWeek = now.getDayOfWeek().getValue();
		LocalDate startWeek = now.minusDays(indexDayOfWeek - 1);
		return startWeek;
	}

	private List<List<InlineKeyboardButton>> getButtons(
		List<? extends TimetableEntity> faculties, Command command) {
		List<List<InlineKeyboardButton>> buttons = new ArrayList<>();
		faculties.forEach(object -> {
			buttons.add(List.of(InlineKeyboardButton.builder()
				.text(object.getTextForCallbackData())
				.callbackData(command.getName()
					+ "," + object.getCallbackData())
				.build()));
		});
		return buttons;
	}

	/**
	 * Handler for {@link Command.FACULTY}
	 */
	@CommandHandlerMethod(Command.FACULTIES)
	private SendMessage sendFaculties(CallbackQuery callback) {
		List<Faculty> faculties = facultiesService.getFaculties();
		return answerTemplate.sendInlineKeyboardButtons(callback
			.getMessage(), "Институты", getButtons(faculties, Command.GROUPS));
	}

	@CommandHandlerMethod(Command.GROUPS)
	private SendMessage sendGroups(CallbackQuery callback) {
		int facultyOid = Integer.valueOf(callback.getData().split(",")[1]);
		List<Group> groups = groupsService.getGroupsByFaculty(facultyOid);
		return answerTemplate.sendInlineKeyboardButtons(callback
			.getMessage(), "Группы", getButtons(groups, Command.LESSONS_ON_THIS_WEEK));
	}

	@CommandHandlerMethod(Command.LESSONS_ON_THIS_WEEK)
	private SendMessage sendLessonsThisWeek(CallbackQuery callback) {
		int groupOid = Integer.valueOf(callback.getData().split(",")[1]);
		LocalDate startWeek = getStartDayOfWeek(LocalDate.now(ZoneId.of("UTC+5")));
		List<Lesson> lessons = lessonsService.getLessonsByGroup(groupOid, startWeek);
		return answerTemplate.sendLessons(callback.getMessage(), lessons, groupOid);
	}

	@CommandHandlerMethod(Command.LESSONS_ON_NEXT_WEEK)
	private SendMessage sendLessonsNextWeek(CallbackQuery callback) {
		int groupOid = Integer.valueOf(callback.getData().split(",")[1]);
		LocalDate startWeek = getStartDayOfWeek(LocalDate.now(ZoneId.of("UTC+5")).plusDays(7));
		List<Lesson> lessons = lessonsService.getLessonsByGroup(groupOid, startWeek);
		return answerTemplate.sendLessons(callback.getMessage(), lessons, groupOid);
	}
}
