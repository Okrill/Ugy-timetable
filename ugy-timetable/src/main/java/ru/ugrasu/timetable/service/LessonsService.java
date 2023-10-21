package ru.ugrasu.timetable.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ru.ugrasu.timetable.model.Lesson;
import ru.ugrasu.timetable.parsers.JsonParser;
import ru.ugrasu.timetable.templates.RequestTemplate;

@Service
public class LessonsService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestTemplate requestTemplate;

	@Autowired
	private JsonParser jsonParser;

	public List<Lesson> getLessonsByGroup(int groupOid, LocalDate startWeek) {
		LocalDate endWeek = startWeek.plusDays(6);
		String lessonsRequest = requestTemplate.getLessonsRequest(startWeek.toString(), endWeek.toString(), groupOid);
		String jsonLessons = restTemplate.getForObject(lessonsRequest, String.class);
		return jsonParser.parse(jsonLessons, Lesson.class);
	}
}
