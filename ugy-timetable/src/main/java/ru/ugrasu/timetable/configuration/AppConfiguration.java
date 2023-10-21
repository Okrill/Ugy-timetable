package ru.ugrasu.timetable.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfiguration {

	@Bean
	SetWebhook setWebhook() {
		return new SetWebhook();
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	ObjectMapper setObjectMapper() {
		return new ObjectMapper();
	}
}
