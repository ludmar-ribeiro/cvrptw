package com.lud.delivery.cvrptw.common.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class DateTimeUtils {

    public static LocalDateTime addMilliseconds(LocalDateTime date, Double milliseconds) {
        return date.plus(milliseconds.longValue(), ChronoField.MILLI_OF_DAY.getBaseUnit());
    }

    public static LocalDateTime ignoreMinutes(LocalDateTime dateTime) {
        return ignoreSeconds(dateTime).with(ChronoField.MINUTE_OF_HOUR, 0l);
    }

    public static LocalDateTime ignoreSeconds(LocalDateTime dateTime) {
        return ignoreMilliseconds(dateTime)
                .with(ChronoField.SECOND_OF_MINUTE, 0l);
    }

    public static LocalDateTime ignoreMilliseconds(LocalDateTime dateTime) {
        return dateTime
                .with(ChronoField.MILLI_OF_SECOND, 0l);
    }
}
