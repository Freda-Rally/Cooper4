package org.freda.cooper4.framework.utils;

import org.joda.time.DateTime;

/**
 *
 * 时间工具类
 *
 * Created by rally on 16/4/22.
 */
public class DateUtils
{
    /**
     * 部分Date输出格式
     */
    private static final String[] FREDA_DATE_PATTERN = {
            "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm",
            "yyyy-MM-dd"
    };

    /**
     *
     * 返回当前时间
     *
     * @return String yyyy-MM-dd HH:mm:ss
     */
    public static String getTime()
    {
        return new DateTime().toString(FREDA_DATE_PATTERN[0]);
    }

    /**
     *
     * 给定格式时间..
     *
     * @param pattern
     * @return String pattern
     */
    public static String getTime(String pattern)
    {
        return  new DateTime().toString(pattern);
    }
}
