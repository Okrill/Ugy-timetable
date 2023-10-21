package ru.ugrasu.timetable.util;

import org.springframework.stereotype.Component;

@Component
public class TextValidate {

	public boolean isCommand(String text) {
		return text.startsWith("/");
	}
}
