package com.example.tradingcompany.formatter;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CustomDateTimeFormatter implements Formatter<LocalDateTime> {

    private static final String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss a";

    @Override
    public LocalDateTime parse(String text, Locale locale) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        return LocalDateTime.parse(text, formatter);
    }

    @Override
    public String print(LocalDateTime date, Locale locale) {
        return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
