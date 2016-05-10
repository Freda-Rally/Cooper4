package org.freda.cooper4.common.service;

/**
 *
 * 初始化所有表的ID序列
 *
 * Created by rally on 16/5/4.
 */
public interface IDSequenceInitService
{
    /**
     * 初始化.将序列放入EhCache.
     */
    public abstract void init();
}
