package com.example.shared.service;

import com.example.shared.entity.ErrorMessage;
import com.example.shared.repository.ErrorMessageRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;

@Service
public class DBErrorMessage {

	private final ErrorMessageRepository errorMessageRepository;

	private static final String DEFAULT_LOCALE_CODE = "en";

	public DBErrorMessage(ErrorMessageRepository errorMessageRepository) {
		this.errorMessageRepository = errorMessageRepository;
	}

	public MessageFormat resolveMessage(String key, Locale locale) {
		ErrorMessage message = errorMessageRepository.findByKeyAndLocale(key, locale.getLanguage());
		if (message == null) {
			message = errorMessageRepository.findByKeyAndLocale(key, DEFAULT_LOCALE_CODE);
		}
		return new MessageFormat(message.getContent(), locale);
	}
}
