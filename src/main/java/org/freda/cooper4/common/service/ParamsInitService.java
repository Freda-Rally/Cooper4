package org.freda.cooper4.common.service;

import javax.servlet.ServletContext;

/**
 *
 * 系统参数初始化接口.
 *
 * Created by rally on 16/5/4.
 */
public interface ParamsInitService
{
    /**
     * 初始化所有参数放入Application中.
     */
    public abstract void init(ServletContext context);
}
