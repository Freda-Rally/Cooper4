package org.freda.cooper4.admin.setting.service;

import org.freda.cooper4.framework.datastructure.Dto;

/**
 *
 * 系统全局参数管理
 *
 * Created by rally on 16/5/5.
 */
public interface ParamsManagerService
{
    /**
     * 添加
     * @param pDto
     */
    public abstract void add(Dto pDto);

    /**
     * 修改
     * @param pDto
     */
    public abstract void edit(Dto pDto);

    /**
     * 删除
     * @param pDto
     */
    public abstract void delete(Dto pDto);
}
