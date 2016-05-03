package org.freda.cooper4.framework.schedulers;

/**
 *
 * 所有任务的父类..
 *
 * Created by rally on 16/4/23.
 */
public abstract class BaseScheduler
{
    /**
     * 所有子类集成后需实现的
     */
    public abstract void execute();
}
