package org.freda.cooper4.admin.setting.service;

import org.freda.cooper4.framework.datastructure.Dto;

/**
 *
 * 权限部分暴露给
 *
 * Created by rally on 16/5/8.
 */
public interface Authority4OrganizationService
{
    /**
     * 人员添加至角色.
     * @param pDto
     */
    public abstract void user2Role(Dto pDto);

    /**
     * 角色添加至人员.
     * @param pDto
     */
    public abstract void role2User(Dto pDto);

    /**
     * 角色绑定菜单.
     * @param pDto
     */
    public abstract void menu2Role(Dto pDto);

    /**
     * 删除用户时,删除角色绑定.
     * @param pDto
     */
    public abstract void delete4UserRm(Dto pDto);

    /**
     * 删除角色时,删除绑定用户与绑定菜单.
     * @param pDto
     */
    public abstract void delete4RoleRm(Dto pDto);

    /**
     * 删除菜单时,删除菜单角色绑定.
     * @param pDto
     */
    public abstract void delete4MenuRm(Dto pDto);
}
