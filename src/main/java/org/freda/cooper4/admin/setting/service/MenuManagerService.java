package org.freda.cooper4.admin.setting.service;

import org.freda.cooper4.framework.datastructure.Dto;

import java.util.List;

/**
 *
 * 菜单资源管理.
 *
 * Created by rally on 16/5/10.
 */
public interface MenuManagerService
{
    /**
     * 添加菜单.
     *
     * @param pDto
     */
    public abstract void add(Dto pDto);

    /**
     * 修改菜单信息.
     *
     * @param pDto
     */
    public abstract void edit(Dto pDto);

    /**
     * 删除菜单.
     *
     * @param pDto
     */
    public abstract void delete(Dto pDto);

    /**
     * 树
     * @param pDto
     * @return
     */
    public abstract List<?> menuTreeInit(Dto pDto);
}
