package ru.ugrasu.timetable.parsers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.InjectableValues;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@Component
public class JsonParser {

	@Autowired
	private ObjectMapper objectMapper;

	@SneakyThrows
	public <T> List<T> parse(String jsonRepresentation, Class<T> castClass) {
		InjectableValues inject = new InjectableValues.Std()
			.addValue(int.class, 1);
		MappingIterator<T> injectetEntity = objectMapper.reader(inject)
			.forType(castClass)
			.readValues(jsonRepresentation);
		List<T> timetableEntityList = new ArrayList<>();
		injectetEntity.forEachRemaining(f -> {
			timetableEntityList.add(f);
		});
		return timetableEntityList;
	}
}
