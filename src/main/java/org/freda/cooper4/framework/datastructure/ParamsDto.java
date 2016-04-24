package org.freda.cooper4.framework.datastructure;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 参数DTO 用于接收Request中的参数
 *
 * Created by rally on 16/4/21.
 */
public interface ParamsDto extends Dto
{
    /**
     *
     * 获取request
     *
     * @return HttpServletRequest
     */
    public HttpServletRequest getRequest();

    /**
     *
     * 获取用户VO
     *
     * @return Object
     */
    public  <T> T getSystemUserVo(Class<T> voClass);

}
