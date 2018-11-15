package com.lud.delivery.cvrptw.common.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

public class DateTimeUtils {

    public static LocalDateTime addMilliseconds(LocalDateTime date, Double milliseconds) {
        return date.plus(milliseconds.longValue(), ChronoField.MILLI_OF_DAY.getBaseUnit());
    }

    public static LocalDateTime ignoreMilliseconds(LocalDateTime dateTime) {
        return dateTime.with(ChronoField.MILLI_OF_SECOND, 0l);
    }
}
