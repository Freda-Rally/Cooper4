package org.freda.cooper4.common.service;

import org.freda.cooper4.common.vo.UserInfoVo;

/**
 *
 * Session监控..
 *
 * Created by rally on 16/5/4.
 */
public interface SessionMonitorService
{
    /**
     * 将登陆用户插入Monitor
     * @param userInfoVo
     */
    public abstract void add4Monitor(UserInfoVo userInfoVo);

    /**
     * 用户退出.删除Monitor
     * @param userInfoVo
     */
    public abstract void del4Monitor(UserInfoVo userInfoVo);
}
