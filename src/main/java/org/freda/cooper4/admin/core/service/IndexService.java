package org.freda.cooper4.admin.core.service;

import org.freda.cooper4.common.vo.UserInfoVo;
import org.freda.cooper4.framework.datastructure.Dto;

import java.util.List;

/**
 *
 * 用于主界面请求与登录的Service
 *
 * Created by rally on 16/5/2.
 */
public interface IndexService
{
    /**
     * 查询菜单 -- 权限
     * @param pDto
     * @return List
     */
    List<Dto> getMenuItem(Dto pDto);

    /**
     * 更改密码.
     * @param pDto
     * @return boolean
     */
    boolean changePassWord(Dto pDto);

    /**
     * 账号密码查询用户.
     * @param pDto
     * @return UserInfoVo
     */
    UserInfoVo queryUser(Dto pDto);
}
