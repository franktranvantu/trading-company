package com.example.tradingcompany.formatter;

import com.example.tradingcompany.dto.DateTimeRange;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class CustomDateTimeRangeFormatter implements Formatter<DateTimeRange> {

    private static final String DATE_FORMAT = "dd/MM/yyyy hh:mm:ss a";

    @Override
    public DateTimeRange parse(String text, Locale locale) throws ParseException {
        String[] dateTimeRanges = text.split("-", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDateTime from = LocalDateTime.parse(dateTimeRanges[0].trim(), formatter);
        LocalDateTime to = LocalDateTime.parse(dateTimeRanges[1].trim(), formatter);
        return new DateTimeRange(from, to);
    }

    @Override
    public String print(DateTimeRange date, Locale locale) {
        String from = date.getFrom().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String to = date.getTo().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return String.format("%s - %s", from, to);
    }
}
