package org.freda.cooper4.framework.web.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * Session 监听
 *
 * Created by rally on 16-4-12.
 */
@WebListener
public class SessionListener implements HttpSessionListener,HttpSessionAttributeListener
{
    private static final Log log = LogFactory.getLog(SessionListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0)
    {
        log.info("");

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
