package ru.ugrasu.timetable.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ru.ugrasu.timetable.model.Group;
import ru.ugrasu.timetable.parsers.JsonParser;
import ru.ugrasu.timetable.templates.RequestTemplate;

@Service
public class GroupsService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RequestTemplate requestTemplate;

	@Autowired
	private JsonParser jsonParser;

	public List<Group> getGroupsByFaculty(int facultyOid) {
		String groupsRequest = requestTemplate.getGroupsRequest();
		String jsonGroups = restTemplate.getForObject(groupsRequest, String.class);
		List<Group> groups = jsonParser.parse(jsonGroups, Group.class);
		return groups.stream()
			.filter(g -> {
				return g.getFacultyOid() == facultyOid;
			})
			.collect(toList());
	}
}
