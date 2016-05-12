package org.freda.cooper4.admin.setting.service.impl;

import org.freda.cooper4.admin.setting.service.SessionManagerService;
import org.freda.cooper4.common.service.SessionMonitorService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.common.vo.UserInfoVo;
import org.freda.cooper4.framework.datastructure.Dto;
import org.springframework.stereotype.Service;

/**
 *
 * SESSION MANAGER SERVICE
 *
 * Created by rally on 16/5/4.
 */
@Service
public class SessionServiceImpl extends Cooper4AdminBaseServiceImpl implements SessionManagerService,SessionMonitorService
{


    /**
     * 杀死会话
     *
     * @param pDto
     */
    @Override
    public void kill(Dto pDto)
    {
        super.getDao().delete("admin.setting.Session.delete",pDto);
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
