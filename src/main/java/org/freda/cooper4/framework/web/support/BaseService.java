package org.freda.cooper4.framework.web.support;

import org.freda.cooper4.framework.utils.SpringBeanLoader;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * 所有Service的父类
 *
 * Created by rally on 16/4/22.
 */
public abstract class BaseService
{
    @Autowired
    protected Cooper4FrameworkTools cooper4Tools;

    /**
     *
     * 如果不使用依赖注入..可动态获取Service实例.
     *
     * @param serviceId
     * @param tClass
     * @param <T>
     * @return T
     */
    protected <T> T getService(String serviceId,Class<T> tClass)
    {
        return SpringBeanLoader.getSpringBean(serviceId, tClass);
    }
}
