package org.freda.cooper4.admin.setting.service.impl;

import org.freda.cooper4.admin.setting.service.SessionManagerService;
import org.freda.cooper4.common.service.SessionMonitorService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.common.vo.UserInfoVo;
import org.freda.cooper4.configs.webmvc.MySessionContext;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.utils.SystemContainer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 *
 * SESSION MANAGER SERVICE
 *
 * Created by rally on 16/5/4.
 */
@Service
public class SessionServiceImpl extends Cooper4AdminBaseServiceImpl implements SessionManagerService,SessionMonitorService
{

    @Resource
    private MySessionContext mySessionContext;
    /**
     * 杀死会话
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void kill(Dto pDto)
    {
       String[] ids = pDto.getAsString("ids").split(",");

        for (String id : ids)
        {
            pDto.put("sessionId",id);

            super.getDao().delete("admin.setting.Session.delete",pDto);

            HttpSession httpSession = mySessionContext.getSession(id);

            httpSession.removeAttribute(SystemContainer.SYSTEM_USER_INFO_VO_KEY);

            mySessionContext.removeSession(httpSession);
        }
    }

    /**
     * 将登陆用户插入Monitor
     *
     * @param userInfoVo
     */
    @Override
    public void add4Monitor(UserInfoVo userInfoVo)
    {
        super.getDao().insert("admin.setting.Session.add",userInfoVo);
    }

    /**
     * 用户退出.删除Monitor
     *
     * @param userInfoVo
     */
    @Override
    public void del4Monitor(UserInfoVo userInfoVo)
    {
        super.getDao().delete("admin.setting.Session.delete",userInfoVo);
    }
}
