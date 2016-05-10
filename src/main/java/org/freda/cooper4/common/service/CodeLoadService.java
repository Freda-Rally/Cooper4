package org.freda.cooper4.common.service;

/**
 *
 * 从Cache中加载Code.
 *
 * Created by rally on 16/5/5.
 */
public interface CodeLoadService
{
    /**
     * 从Cache中获取Code
     * @return
     */
    public abstract String getFormCache();
}
