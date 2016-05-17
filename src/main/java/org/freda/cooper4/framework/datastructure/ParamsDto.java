package org.freda.cooper4.framework.datastructure;

import org.springframework.web.multipart.MultipartHttpServletRequest;

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
     * 获得上传request
     * @return
     */
    public MultipartHttpServletRequest getMultipartHttpServletRequest();

    /**
     *
     * 获取用户VO
     *
     * @return Object
     */
    public  <T> T getSystemUserVo(Class<T> voClass);

}
