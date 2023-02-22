package org.icarus.tasks.controllers;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtils {

    private static final String DATE_TIME_PATTERN = "dd/MM/yyyy HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public String formatToString(final LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_FORMATTER);
    }

}
