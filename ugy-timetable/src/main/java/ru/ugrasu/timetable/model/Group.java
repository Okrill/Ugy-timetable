package ru.ugrasu.timetable.model;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Group implements TimetableEntity {

	@JacksonInject
	private int id;

	private String name;

	private int facultyOid;

	private int groupOid;

	private String speciality;

	@Override
	public String getCallbackData() {
		return String.valueOf(groupOid);
	}

	@Override
	public String getTextForCallbackData() {

		if (speciality.isBlank()) {
			return name;
		}
		return name + " - " + speciality;
	}
}
