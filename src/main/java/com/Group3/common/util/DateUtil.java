package com.Group3.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * 获取当前时间前一小时的时间
     * @param date
     * @return java.util.Date
     */
    public static Date beforeOneHourToNowDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, -1);
        return calendar.getTime();
    }

    /**
     * 获取当前时间后一小时的时间
     * @param date
     * @return java.util.Date
     */
    public static Date afterOneHourToNowDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        /* HOUR_OF_DAY 指示一天中的小时 */
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 1);
        return calendar.getTime();
    }
}
