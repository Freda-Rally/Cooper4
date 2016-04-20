package org.freda.cooper4.framework.web.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * 系统启动监听
 *
 * Created by rally on 16-4-12.
 */
@WebListener
public class SystemInitListener implements ServletContextListener
{

    private static final Log log = LogFactory.getLog(SystemInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        log.info("system is starting!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        log.info("system is destroyed!");
    }
}
