package com.example.bankpoc.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHour {

    public static String format(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return localDateTime.format(formatter);
    }
}
