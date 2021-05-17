package com.example.tradingcompany.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DateTimeRange {

    private LocalDateTime from;
    private LocalDateTime to;

}
