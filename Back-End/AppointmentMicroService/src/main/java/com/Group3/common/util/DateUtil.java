package com.Group3.common.util;

import com.Group3.common.constant.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * Gets the time one hour before the current time
     * @param date
     * @return java.util.Date
     */
    public static Date beforeOneHourToNowDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY Indicates the hour of the day */
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        return calendar.getTime();
    }

    /**
     * Obtain the time one hour after the current time
     * @param date
     * @return java.util.Date
     */
    public static Date afterOneHourToNowDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY Indicates the hour of the day */
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }

    /**
     * Subtract the date after the corresponding time
     *
     * @param date   Date to add or subtract time
     * @param minute Time added or subtracted (minutes)
     * @return Add or subtract the date after the corresponding time
     */
    public static Date subtractTime(Date date, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = sdf.format(date.getTime() - minute * Constants.ONE_MINUTE);
        Date time = null;
        try {
            time = sdf.parse(strTime);
        } catch (ParseException e) {
            System.out.println("Time error");
            e.printStackTrace();
        }
        return time;
    }

    /**
     * Add the date after the corresponding time
     *
     * @param date   Date to add or subtract time
     * @param minute Time added or subtracted (minutes)
     * @return Add or subtract the date after the corresponding time
     */
    public static Date addTime(Date date, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = sdf.format(date.getTime() + minute * Constants.ONE_MINUTE);
        Date time = null;
        try {
            time = sdf.parse(strTime);
        } catch (ParseException e) {
            System.out.println("Time error");
            e.printStackTrace();
        }
        return time;
    }
}
