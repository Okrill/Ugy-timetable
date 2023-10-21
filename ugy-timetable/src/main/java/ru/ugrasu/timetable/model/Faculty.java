package ru.ugrasu.timetable.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Faculty implements TimetableEntity {

	@JacksonInject
	private int id;

	private String abbr;

	private int facultyOid;

	private String name;

	private String institute;

	@Override
	public String getCallbackData() {
		return String.valueOf(facultyOid);
	}

	@Override
	public String getTextForCallbackData() {
		return name;
	}
}
