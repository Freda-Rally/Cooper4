package org.freda.cooper4.framework.utils;

/**
 *
 * 系统Container
 *
 * Created by rally on 16-4-12.
 */
public class SystemContainer
{
    /**
     * 系统基础
     */
    public static final boolean TRUE = new Boolean(true);

    public static final boolean FALSE = new Boolean(false);

    /**
     *系统TIPS
     */
    public static final String TIPS_SUCCESS_MSG = "恭喜您:操作成功!";

    public static final String TIPS_ERROR_MSG = "很遗憾:操作失败!";

    /**
     * 用户信息SessionKey
     */
    public static final String SYSTEM_USER_INFO_VO_KEY = "SYSTEM_USER_INFO_VO_KEY";

    /**
     * 系统日期格式化参数
     */
    public static final String[] DATE_TIME_FORMART = {
            "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd HH:mm","yyyy-MM-dd",
            "yyyyMMdd","yyyyMM"};
}
