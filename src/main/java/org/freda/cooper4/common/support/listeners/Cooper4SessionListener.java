package org.freda.cooper4.common.support.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * Cooper4 系统监听.
 *
 * Created by rally on 16/4/30.
 */
@WebListener
public class Cooper4SessionListener implements HttpSessionListener,HttpSessionAttributeListener
{
    private static final Log log = LogFactory.getLog(Cooper4SessionListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0)
    {
        log.debug("");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0)
    {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0)
    {

    }

    @Override
    public void sessionCreated(HttpSessionEvent arg0)
    {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent arg0)
    {

    }
}
