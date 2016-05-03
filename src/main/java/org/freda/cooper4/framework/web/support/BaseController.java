package org.freda.cooper4.framework.web.support;

import org.freda.cooper4.framework.datastructure.ParamsDto;
import org.freda.cooper4.framework.datastructure.impl.BaseDto;
import org.freda.cooper4.framework.datastructure.impl.BaseParamsDto;
import org.freda.cooper4.framework.utils.SpringBeanLoader;
import org.freda.cooper4.framework.utils.SystemContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * 所有Controller的父类
 *
 * Created by rally on 16/4/22.
 */
public abstract class BaseController
{
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
        return SpringBeanLoader.getSpringBean(serviceId,tClass);
    }

    /**
     * 获取一个Session属性对象
     *
     * @param request
     * @param sessionKey
     * @return
     */
    protected Object getSessionAttribute(HttpServletRequest request, String sessionKey)
    {
        HttpSession session = request.getSession(false);

        return session != null ? session.getAttribute(sessionKey) : null;
    }

    /**
     * 设置一个Session属性对象
     *
     * @param request
     * @param objSessionAttribute
     * @return
     */
    protected void setSessionAttribute(HttpServletRequest request, String sessionKey, Object objSessionAttribute)
    {
        HttpSession session = request.getSession();

        if (session != null)
            session.setAttribute(sessionKey, objSessionAttribute);
    }

    /**
     * 移除Session对象属性值
     *
     * @param request
     * @param sessionKey
     * @return
     */
    protected void removeSessionAttribute(HttpServletRequest request, String sessionKey)
    {
        HttpSession session = request.getSession();

        if (session != null)
            session.removeAttribute(sessionKey);
    }

    /**
     * 输出响应
     *
     * @param str
     * @throws IOException
     */
    protected void write(String str, HttpServletResponse response) throws IOException
    {
        response.getWriter().write(str);

        response.getWriter().flush();

        response.getWriter().close();
    }

    /**
     *
     * 交易成功提示信息
     *
     * @param pMsg
     *            提示信息
     * @param response
     * @return
     * @throws IOException
     */
    protected void setOkTipMsg(String pMsg, HttpServletResponse response) throws IOException
    {
        write(new BaseDto(SystemContainer.TRUE, pMsg).toJson(), response);
    }

    /**
     *
     * 交易失败提示信息(特指：业务交易失败并不是请求失败)<br>
     * 和Form的submit中的failur回调对应,Ajax.request中的failur回调是值请求失败
     *
     * @param pMsg
     *            提示信息
     * @param response
     * @return
     * @throws IOException
     */
    protected void setErrTipMsg(String pMsg, HttpServletResponse response) throws IOException
    {
        write(new BaseDto(SystemContainer.FALSE, pMsg).toJson(), response);
    }
    /**
     * 将所有前台参数封装成DTO
     * @param request
     * @return
     */
    protected ParamsDto getParamsAsDto(HttpServletRequest request)
    {
        return new BaseParamsDto(request);
    }
}
