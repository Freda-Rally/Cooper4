package org.freda.cooper4.framework.utils;

import org.springframework.context.ApplicationContext;

/**
 *
 *
 *
 * Created by rally on 16/4/22.
 */
public class SpringBeanLoader
{
    private static ApplicationContext applicationContext;

    /**
     *
     * 获取SpringApplicationContext
     *
     * @return ApplicationContext
     */

    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    /**
     *
     * 设置SpringApplicationContext
     *
     * @param applicationContext
     */
    public static void setApplicationContext(ApplicationContext applicationContext)
    {
        SpringBeanLoader.applicationContext = applicationContext;
    }

    /**
     *
     * 获取Spring中注册的Bean
     *
     * @param beanClass
     * @param beanId
     * @return
     */
    public static <T> T getSpringBean(String beanId,Class<T> beanClass)
    {
        return getApplicationContext().getBean(beanId,beanClass);
    }
}
