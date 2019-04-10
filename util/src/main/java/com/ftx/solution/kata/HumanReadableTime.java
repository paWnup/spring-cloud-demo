package com.ftx.solution.kata;

/**
 * Write a function, which takes a non-negative integer (seconds) as
 * input and returns the time in a human-readable format (HH:MM:SS)
 * <p>
 * HH = hours, padded to 2 digits, range: 00 - 99
 * MM = minutes, padded to 2 digits, range: 00 - 59
 * SS = seconds, padded to 2 digits, range: 00 - 59
 * The maximum time never exceeds 359999 (99:59:59)
 *
 * @author puan
 * @date 2019-04-10 13:49
 **/
public class HumanReadableTime {

    public static String makeReadable(int seconds) {
        return String.format("%02d:%02d:%02d", seconds / 3600, (seconds / 60) % 60, seconds % 60);
    }

    public static String makeReadable1(int seconds) {
        // Do something
        if (seconds > 359999) {
            return "";
        }
        int minutes = seconds / 60;
        int second = seconds % 60;
        int hour = minutes / 60;
        int minute = minutes % 60;
        return getTimeString(hour) + ":" + getTimeString(minute) + ":" + getTimeString(second);
    }

    private static String getTimeString(int timeNumber) {
        if (timeNumber < 10) {
            return "0" + timeNumber;
        } else {
            return "" + timeNumber;
        }
    }
}
