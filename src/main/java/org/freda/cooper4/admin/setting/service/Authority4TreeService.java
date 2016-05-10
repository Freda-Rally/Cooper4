package org.freda.cooper4.admin.setting.service;

import org.freda.cooper4.framework.datastructure.Dto;

import java.util.List;

/**
 *
 * 附带权限的树系列..
 *
 * Created by rally on 16/5/9.
 */
public interface Authority4TreeService
{
    /**
     * 部门人员附带权限选中树..用于角色绑定人员.
     * @param pDto
     * @return
     */
    public abstract List userAndDeptTree4Role(Dto pDto);

    /**
     * 角色附带权限选中树..用于角色绑定人员.
     * @param pDto
     * @return
     */
    public abstract List roleTree4User(Dto pDto);

    /**
     * 菜单树附带权限选中树..用于菜单绑定角色.
     * @param pDto
     * @return
     */
    public abstract List menuTree4Role(Dto pDto);
}
