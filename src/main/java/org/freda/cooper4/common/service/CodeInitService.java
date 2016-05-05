package org.freda.cooper4.common.service;

/**
 *
 * 系统数据字典.初始化.
 *
 * Created by rally on 16/5/4.
 */
public interface CodeInitService
{
    /**
     * 初始化系统数据字典.将字典参数放入EhCache.
     */
    public abstract void init();

    /**
     * 销毁
     */
    public abstract void destroyed();
}