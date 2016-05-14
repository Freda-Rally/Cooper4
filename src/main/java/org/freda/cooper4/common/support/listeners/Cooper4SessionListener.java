package org.freda.cooper4.common.support.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.common.service.SessionMonitorService;
import org.freda.cooper4.common.vo.UserInfoVo;
import org.freda.cooper4.framework.utils.FredaUtils;
import org.freda.cooper4.framework.utils.SystemContainer;

import javax.annotation.Resource;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * Cooper4 系统监听.
 *
 * Created by rally on 16/4/30.
 */
@WebListener
public class Cooper4SessionListener implements HttpSessionListener,HttpSessionAttributeListener
{
    private static final Log log = LogFactory.getLog(Cooper4SessionListener.class);

    @Resource
    private SessionMonitorService sessionMonitorService;

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0)
    {
        if (arg0.getName().equals(SystemContainer.SYSTEM_USER_INFO_VO_KEY))
        {
            UserInfoVo userInfoVo = (UserInfoVo) arg0.getValue();

            userInfoVo.setSessionId(arg0.getSession().getId());

            sessionMonitorService.add4Monitor(userInfoVo);

            log.debug(userInfoVo.getAccount() + " : 登录成功.. SessionId : " + userInfoVo.getSessionId() + ".. 时间 : " + FredaUtils.getCurrentTime());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0)
    {
        if (arg0.getName().equals(SystemContainer.SYSTEM_USER_INFO_VO_KEY))
        {
            UserInfoVo userInfoVo = (UserInfoVo) arg0.getValue();

            userInfoVo.setSessionId(arg0.getSession().getId());

            sessionMonitorService.del4Monitor(userInfoVo);

            log.debug(userInfoVo.getAccount() + " : 退出成功.. SessionId : " + userInfoVo.getSessionId() + ".. 时间 : " + FredaUtils.getCurrentTime());
        }
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0)
    {

    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0)
    {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0)
    {
//        UserInfoVo userInfoVo = new UserInfoVo();
//
//        userInfoVo.setSessionId(arg0.getSession().getId());
//
//        sessionMonitorService.del4Monitor(userInfoVo);
    }
}
