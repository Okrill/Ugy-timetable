package ru.ugrasu.timetable.templates;

import org.springframework.stereotype.Component;

@Component
public class RequestTemplate {

	private static final String getFacultiesRequest = "https://www.ugrasu.ru/api/directory/faculties";

	private static final String getGroupsRequest = "https://www.ugrasu.ru/api/directory/groups";

	public String getLessonsRequest(String fromDate, String toDate, int groupOid) {
		return "https://www.ugrasu.ru/api/directory/"
			+ "lessons?"
			+ "fromdate=" + fromDate
			+ "&todate=" + toDate
			+ "&groupOid=" + groupOid;
	}

	public String getFacultiesRequst() {
		return getFacultiesRequest;
	}

	public String getInstitutesRequst() {
		return getFacultiesRequest;
	}

	public String getGroupsRequest() {
		return getGroupsRequest;
	}
}
