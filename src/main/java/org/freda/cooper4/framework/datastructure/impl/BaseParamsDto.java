package org.freda.cooper4.framework.datastructure.impl;

import org.freda.cooper4.framework.datastructure.ParamsDto;
import org.freda.cooper4.framework.utils.SystemContainer;
import org.freda.cooper4.framework.utils.WebUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * 请求参数DTO
 *
 * Created by rally on 16/4/21.
 */
public class BaseParamsDto extends BaseDto implements ParamsDto
{
    private HttpServletRequest request;

    private BaseVo userInfoVo;

    private static final String HTTP_REQUEST_DTO_KEY = "HTTP_REQUEST_DTO_KEY";

    private static final String SYSTEM_USER_VO = "SYSTEM_USER_VO";

    /**
     *
     *
     *
     * @param request
     */
    public BaseParamsDto(HttpServletRequest request)
    {
        this.put(HTTP_REQUEST_DTO_KEY,request);

        WebUtils.setParams2PDto(this);

        this.put(SYSTEM_USER_VO, WebUtils.getSessionAttribute(
                this.getRequest(),SystemContainer.SYSTEM_USER_INFO_VO_KEY));
    }
    /**
     *
     * @return HttpServletRequest
     */
    @Override
    public HttpServletRequest getRequest()
    {
        return (HttpServletRequest)this.get(HTTP_REQUEST_DTO_KEY);
    }

    /**
     * 获得上传request
     *
     * @return
     */
    @Override
    public MultipartHttpServletRequest getMultipartHttpServletRequest()
    {
        return (MultipartHttpServletRequest)this.getRequest();
    }

    /**
     *
     * @param voClass
     * @param <T>
     * @return T
     */
    @Override
    public <T> T getSystemUserVo(Class<T> voClass)
    {
        return this.containsKey(SYSTEM_USER_VO) ?
                voClass.cast(this.get(SYSTEM_USER_VO)) :
                voClass.cast(WebUtils.getSessionAttribute(this.getRequest(), SystemContainer.SYSTEM_USER_INFO_VO_KEY));
    }
}
