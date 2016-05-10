package org.freda.cooper4.admin.setting.service.impl;

import org.freda.cooper4.admin.setting.service.Authority4OrganizationService;
import org.freda.cooper4.admin.setting.service.Authority4TreeService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.utils.FredaUtils;
import org.freda.cooper4.framework.utils.SystemContainer;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * 权限服务.
 *
 * Created by rally on 16/5/9.
 */
public class AuthorityServiceImpl extends Cooper4AdminBaseServiceImpl implements Authority4OrganizationService,Authority4TreeService
{
    /**
     * 人员添加至角色.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void user2Role(Dto pDto)
    {
        String[] ids = pDto.getAsString("ids").split(",");

        super.getDao().delete("",pDto);

        for (String id : ids)
        {
            if (FredaUtils.isNotEmpty(id))
            {
                pDto.put("userId",id);

                super.getDao().insert("",pDto);
            }
        }
    }

    /**
     * 角色添加至人员.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void role2User(Dto pDto)
    {
        String[] ids = pDto.getAsString("ids").split(",");

        super.getDao().delete("",pDto);

        for (String id : ids)
        {
            pDto.put("roleId",id);

            super.getDao().insert("",pDto);
        }
    }

    /**
     * 角色绑定菜单.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void menu2Role(Dto pDto)
    {
        String[] ids = pDto.getAsString("ids").split(",");

        super.getDao().delete("",pDto);

        for (String id : ids)
        {
            pDto.put("menuId",id);

            super.getDao().insert("",pDto);
        }
    }

    /**
     * 删除用户时,删除角色绑定.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete4UserRm(Dto pDto)
    {
        super.getDao().delete("",pDto);
    }

    /**
     * 删除角色时,删除绑定用户与绑定菜单.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete4RoleRm(Dto pDto)
    {
        super.getDao().delete("",pDto);

        super.getDao().delete("",pDto);
    }

    /**
     * 删除菜单时,删除菜单角色绑定.
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.MANDATORY)
    public void delete4MenuRm(Dto pDto)
    {
        super.getDao().delete("",pDto);
    }

    /**
     * 部门人员附带权限选中树..用于角色绑定人员.
     *
     * @param pDto
     * @return
     */
    @Override
    public List userAndDeptTree4Role(Dto pDto)
    {


        return null;
    }

    /**
     * 角色附带权限选中树..用于角色绑定人员.
     *
     * @param pDto
     * @return
     */
    @Override
    public List roleTree4User(Dto pDto)
    {
        return null;
    }

    /**
     * 菜单树附带权限选中树..用于菜单绑定角色.
     *
     * @param pDto
     * @return
     */
    @Override
    public List menuTree4Role(Dto pDto)
    {
        return null;
    }

    /**
     * 设置节点LEAF
     * @param data
     * @param leaf
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<?> setListLeaf(List<?> data,final int leaf)
    {
        for(int i=0;i<data.size();i++)
        {
            Dto rowDto = (Dto) data.get(i);

            rowDto.put("leaf", leaf);
        }
        return data;
    }
    /**
     * 添加checked
     * @param data
     * @param falg
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<?> setListChecked(List<?> data,final String falg)
    {
        for(int i=0;i<data.size();i++)
        {
            Dto rowDto = (Dto) data.get(i);

            if(FredaUtils.isNotEmpty(rowDto.getAsString(falg)))
            {
                rowDto.put("checked", SystemContainer.TRUE);
            }
            else
            {
                rowDto.put("checked", SystemContainer.FALSE);
            }
        }
        return data;
    }
}
