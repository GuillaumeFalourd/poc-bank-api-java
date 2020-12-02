package com.example.bankpoc.util;

import static junit.framework.TestCase.assertNotNull;

import java.time.LocalDateTime;

import org.junit.Test;

public class DateHourTest {

    @Test
    public void formatTest_PassingCorrectDateShouldReturnStringWithDateFormatted() {
        DateHour dateHour = new DateHour();
        String dateTime = DateHour.format(LocalDateTime.now());
        assertNotNull(dateTime);
    }
}
