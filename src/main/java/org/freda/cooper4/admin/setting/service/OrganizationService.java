package org.freda.cooper4.admin.setting.service;

import org.freda.cooper4.framework.datastructure.Dto;

import java.io.IOException;

/**
 *
 * 人员.部门.角色.修改/新增/删除.
 *
 * Created by rally on 16/5/8.
 */
public interface OrganizationService
{
    /**
     * 人员新增
     * @param pDto
     */
    public abstract boolean userAdd(Dto pDto) throws IOException;

    /**
     * 人员信息修改.
     * @param pDto
     */
    public abstract void userEdit(Dto pDto)throws IOException;

    /**
     * 人员删除.
     * @param pDto
     */
    public abstract void userDelete(Dto pDto);

    /**
     * 部门新增.
     * @param pDto
     */
    public abstract void deptAdd(Dto pDto);

    /**
     * 部门信息修改.
     * @param pDto
     */
    public abstract void deptEdit(Dto pDto);

    /**
     * 部门删除.
     * @param pDto
     */
    public abstract void deptDelete(Dto pDto);

    /**
     * 角色新增.
     * @param pDto
     */
    public abstract void roleAdd(Dto pDto);

    /**
     * 角色信息修改.
     * @param pDto
     */
    public abstract void roleEdit(Dto pDto);

    /**
     * 角色删除.
     * @param pDto
     */
    public abstract void roleDelete(Dto pDto);
}
