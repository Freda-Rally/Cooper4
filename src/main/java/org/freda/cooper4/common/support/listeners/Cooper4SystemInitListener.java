package org.freda.cooper4.common.support.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.common.service.CodeInitService;
import org.freda.cooper4.common.service.IDSequenceInitService;
import org.freda.cooper4.common.service.ParamsInitService;

import javax.annotation.Resource;
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

    @Resource
    CodeInitService codeInitService;

    @Resource
    ParamsInitService paramsInitService;

    @Resource
    IDSequenceInitService idSequenceInitService;

    @Override
    public void contextInitialized(ServletContextEvent sce)
    {
        //加载Code
        codeInitService.init();
        //加载Params
        paramsInitService.init(sce.getServletContext());
        //加载数据库ID主键
        idSequenceInitService.init();

        log.info("Cooper4 system is starting!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce)
    {
        log.info("Cooper4 system is destroyed!");
    }
}
