package org.freda.cooper4.common.support.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 *
 * 系统启停监听.
 *
 * Created by rally on 16/4/30.
 */
@WebListener
public class Cooper4SystemInitListener implements ServletContextListener
{
    private static final Log log = LogFactory.getLog(Cooper4SystemInitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        log.info("Cooper4 system is starting!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        log.info("Cooper4 system is destroyed!");
    }
}
