package com.Group3.common.util;

import com.Group3.common.constant.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    /**
     * 减对应时间后的日期
     *
     * @param date   需要加减时间的日期
     * @param minute 加减的时间(分钟)
     * @return 加减对应时间后的日期
     */
    public static Date subtractTime(Date date, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = sdf.format(date.getTime() - minute * Constants.ONE_MINUTE);
        Date time = null;
        try {
            time = sdf.parse(strTime);
        } catch (ParseException e) {
            System.out.println("时间错误");
            e.printStackTrace();
        }
        return time;
    }

    /**
     * 加对应时间后的日期
     *
     * @param date   需要加减时间的日期
     * @param minute 加减的时间(分钟)
     * @return 加减对应时间后的日期
     */
    public static Date addTime(Date date, int minute) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime = sdf.format(date.getTime() + minute * Constants.ONE_MINUTE);
        Date time = null;
        try {
            time = sdf.parse(strTime);
        } catch (ParseException e) {
            System.out.println("时间错误");
            e.printStackTrace();
        }
        return time;
    }
}
