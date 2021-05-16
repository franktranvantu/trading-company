package com.example.tradingcompany.formatter;

import com.example.tradingcompany.dto.DateRange;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Component
public class DateRangeFormatter implements Formatter<DateRange> {

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    @Override
    public DateRange parse(String text, Locale locale) throws ParseException {
        String[] dateRangeTexts = text.split("-", 2);
        DateRange dateRange = new DateRange();
        dateRange.setFrom(LocalDate.parse(StringUtils.trim(dateRangeTexts[0]), DateTimeFormatter.ofPattern(DATE_FORMAT)));
        dateRange.setTo(LocalDate.parse(StringUtils.trim(dateRangeTexts[1]), DateTimeFormatter.ofPattern(DATE_FORMAT)));
        return dateRange;
    }

    @Override
    public String print(DateRange dateRange, Locale locale) {
        String from = dateRange.getFrom().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        String to = dateRange.getTo().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
        return String.format("%s - %s", from, to);
    }
}
