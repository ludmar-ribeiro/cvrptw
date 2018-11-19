package com.lud.delivery.cvrptw.common.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

/**
 * Static methods to transform a {@link LocalDateTime}
 *
 * @author Ludmar Ribeiro
 *
 */
public class DateTimeUtils {

    /**
     * Add milliseconds to a {@link LocalDateTime} 
     *
     * @param dateTime
     * @param milliseconds
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime addMilliseconds(LocalDateTime dateTime, Double milliseconds) {
        return dateTime.plus(milliseconds.longValue(), ChronoField.MILLI_OF_DAY.getBaseUnit());
    }

    /**
     * Remove minutes of a {@link LocalDateTime}
     *
     * @param dateTime
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ignoreMinutes(LocalDateTime dateTime) {
        return ignoreSeconds(dateTime).with(ChronoField.MINUTE_OF_HOUR, 0l);
    }

    /**
     * Remove seconds of a {@link LocalDateTime}
     *
     * @param dateTime
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ignoreSeconds(LocalDateTime dateTime) {
        return ignoreMilliseconds(dateTime)
                .with(ChronoField.SECOND_OF_MINUTE, 0l);
    }

    /**
     * Remove milliseconds of a {@link LocalDateTime}
     *
     * @param dateTime
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime ignoreMilliseconds(LocalDateTime dateTime) {
        return dateTime
                .with(ChronoField.MILLI_OF_SECOND, 0l);
    }
}
