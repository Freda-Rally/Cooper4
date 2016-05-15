package org.freda.cooper4.configs.webmvc;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 *
 *
 * Created by rally on 16/5/15.
 */
@Component
public class MySessionContext
{
    private HashMap mySessionMap;

    public MySessionContext()
    {
        mySessionMap = new HashMap();
    }

    public synchronized void addSession(HttpSession session)
    {
        if(session!=null)
        {
            mySessionMap.put(session.getId(), session);
        }
    }

    public synchronized void removeSession(HttpSession session)
    {
        if(session!=null)
        {
            mySessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String session_id)
    {
        if(session_id==null)
            return null;
        return (HttpSession)mySessionMap.get(session_id);
    }

}
