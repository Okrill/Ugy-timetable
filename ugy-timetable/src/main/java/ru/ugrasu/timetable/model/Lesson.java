package ru.ugrasu.timetable.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lesson implements TimetableEntity {

	@JacksonInject
	public int id;

	public int lessonOid;

	public String auditorium;

	public String beginLesson;

	public String endLesson;

	public int dayOfWeek;

	public String date;

	public String discipline;

	public String kindOfWork;

	public String lecturer;

	public String subGroup;

	public String dayOfWeekString;

	public String getLegendColorOfWork() {

		switch (kindOfWork) {
		case "Лекция":
			return "🟢";

		case "Лабораторные работы":
			return "🔵";

		default:
			return "🟡";
		}
	}

	@Override
	public String toString() {
		String lesson = getLegendColorOfWork() + " " + beginLesson + " - " + endLesson + " " + kindOfWork + "\n" +
			discipline + " \n" + auditorium + "\n"
			+ lecturer;

		if (subGroup != null) {
			lesson += " \n" + subGroup;
		}
		return lesson + "\n";
	}

	@Override
	public String getCallbackData() {
		return String.valueOf(lessonOid);
	}

	@Override
	public String getTextForCallbackData() {
		return discipline;
	}
}
