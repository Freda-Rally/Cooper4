package org.freda.cooper4.admin.setting.controller;

import com.github.pagehelper.Page;
import org.freda.cooper4.admin.setting.service.SessionManagerService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseController;
import org.freda.cooper4.framework.json.JsonHelper;
import org.freda.cooper4.framework.utils.SystemContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Session管理
 *
 * Created by rally on 16/5/15.
 */
@Controller
@RequestMapping(value = "/session")
public class SessionController extends Cooper4AdminBaseController
{
    @Resource
    private SessionManagerService sessionManagerService;

    /**
     * 查询
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list4Page")
    public String list4Page(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        Page page = cooper4Reader.queryForPage("admin.setting.Session.list4Page",super.getParamsAsDto(request));

        super.write(JsonHelper.encodeList2PageJson(page.getResult(),page.getTotal(), SystemContainer.DATE_TIME_FORMART[0]),response);

        return null;
    }

    /**
     * 杀死会话
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/kill")
    public String kill(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        sessionManagerService.kill(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }
}
