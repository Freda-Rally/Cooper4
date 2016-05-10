package org.freda.cooper4.admin.setting.service;

import org.freda.cooper4.framework.datastructure.Dto;

/**
 *
 * 会话管理
 *
 * Created by rally on 16/5/5.
 */
public interface SessionManagerService
{
    /**
     * 杀死会话
     * @param pDto
     */
    public abstract void kill(Dto pDto);
}
