package org.freda.cooper4.admin.setting.service;

import org.freda.cooper4.framework.datastructure.Dto;

/**
 *
 * CODE 管理
 *
 * Created by rally on 16/5/5.
 */
public interface CodeManagerService
{
    /**
     * 添加
     */
    public abstract void add(Dto pDto);

    /**
     * 修改
     */
    public abstract void edit(Dto pDto);

    /**
     * 删除
     */
    public abstract void delete(Dto pDto);
}
