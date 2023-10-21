package ru.ugrasu.timetable.commands;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Set of command
 */
@Getter
@RequiredArgsConstructor
public enum Command {

	START("/start", true),
	FACULTIES("FACULTIES", false),
	GROUPS("GROUPS", false),
	LESSONS_ON_THIS_WEEK("LESSONS_ON_THIS_WEEK", false),
	LESSONS_ON_NEXT_WEEK("LESSONS_ON_NEXT_WEEK", false),
	ERROR("ERROR", false);

	private final String name;

	private final boolean isLineCommand;
}