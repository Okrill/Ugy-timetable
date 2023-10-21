package ru.ugrasu.timetable.handler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import ru.ugrasu.timetable.bpp.HandlerInjectorBPP;
import ru.ugrasu.timetable.bpp.annotation.CommandHandlerMethod;
import ru.ugrasu.timetable.commands.Command;
import ru.ugrasu.timetable.model.HandlerDefenition;

/**
 * Class that {@link HandlerInjectorBPP} fill by {@link CommandHandlerMethod}
 * and map commands by {@link HandlerDefenition} for use their in
 * {@link CommandHandlerFactory} for beanname and {@link CommandHandler} for
 * method invoke
 */
public final class HandlerDefenitionStore {

	private static final Map<Command, HandlerDefenition> handlersStore = new HashMap<>();

	public static final void put(Command command, HandlerDefenition handlerDef) {
		handlersStore.put(command, handlerDef);
	}

	public static final void put(Command command, String beanName, Method method) {
		handlersStore.put(command, new HandlerDefenition(beanName, method));
	}

	public static final Method getMethod(Command command) {
		return handlersStore.get(command).getMethod();
	}

	public static final String getBeanName(Command command) {
		return handlersStore.get(command).getBeanName();
	}

	public static final boolean isNotPresent(Command command) {
		return !handlersStore.containsKey(command);
	}

}
