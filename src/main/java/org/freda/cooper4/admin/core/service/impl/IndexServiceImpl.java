package org.freda.cooper4.admin.core.service.impl;

import org.freda.cooper4.admin.core.service.IndexService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.common.vo.UserInfoVo;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.utils.CodecUtils;
import org.freda.cooper4.framework.utils.FredaUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * 主页面--登录页面部分.主页面的加载等..
 *
 * Created by rally on 16/5/2.
 */
@Service(value = "indexService")
public class IndexServiceImpl extends Cooper4AdminBaseServiceImpl implements IndexService
{
    /**
     * 查询菜单 -- 权限
     * @param pDto
     * @return List
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Dto> getMenuItem(Dto pDto)
    {
        if (FredaUtils.isEmpty(pDto.getAsString("pId")))
        {
            pDto.put("pId",pDto.getAsString("node"));
        }
        pDto.put("userId", super.getUserInfoVo(pDto).getUserId());

        pDto.put("userType",super.getUserInfoVo(pDto).getUserType());

        return super.getDao().queryForList("Admin.Core.queryMenu", pDto);
    }

    /**
     * 更改密码.
     * @param pDto
     * @return boolean
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean changePassWord(Dto pDto)
    {
        pDto.put("userId",super.getUserInfoVo(pDto).getUserId());

        pDto.put("oldPwd",CodecUtils.decryptBasedDes(pDto.getAsString("oldPwd")));

        pDto.put("newPwd",CodecUtils.decryptBasedDes(pDto.getAsString("newPwd")));

        if (super.getDao().update("Admin.Core.editPwd", pDto) > 0)
        {
            return true;
        }
        return false;
    }

    /**
     * 账号密码查询用户.
     * @param pDto
     * @return UserInfoVo
     */
    @Override
    @SuppressWarnings("unchecked")
    public UserInfoVo queryUser(Dto pDto)
    {
        pDto.put("password", CodecUtils.encryptBasedDes(pDto.getAsString("password")));

        return (UserInfoVo)super.getDao().queryForObject("Admin.Core.queryUser", pDto);
    }
}
