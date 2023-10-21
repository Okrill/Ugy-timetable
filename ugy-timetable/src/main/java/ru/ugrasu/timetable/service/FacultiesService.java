package ru.ugrasu.timetable.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ru.ugrasu.timetable.model.Faculty;
import ru.ugrasu.timetable.parsers.JsonParser;
import ru.ugrasu.timetable.templates.RequestTemplate;

@Service
public class FacultiesService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestTemplate requestTemplate;

	@Autowired
	private JsonParser jsonParser;

	public List<Faculty> getFaculties() {
		String facultiesRequst = requestTemplate.getFacultiesRequst();
		String jsonFaculties = restTemplate.getForObject(facultiesRequst, String.class);
		List<Faculty> faculties = jsonParser.parse(jsonFaculties, Faculty.class);
		return faculties.stream()
			.filter(f -> {
				return !f.getName().equals("!Служебный")
					&& !f.getInstitute().isBlank();
			})
			.collect(toList());
	}
}
