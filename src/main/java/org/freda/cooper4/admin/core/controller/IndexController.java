package org.freda.cooper4.admin.core.controller;

import org.freda.cooper4.admin.core.service.IndexService;
import org.freda.cooper4.admin.core.utils.CoreContainer;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseController;
import org.freda.cooper4.common.vo.UserInfoVo;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.json.JsonHelper;
import org.freda.cooper4.framework.utils.FredaUtils;
import org.freda.cooper4.framework.utils.SystemContainer;
import org.freda.cooper4.framework.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 主页面--登录页面部分.主页面的加载等..
 *
 * Created by rally on 16/4/25.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends Cooper4AdminBaseController
{
    @Resource(name = "indexService")
    private IndexService indexService;

    private static final String TO_CONTROLLER_HEAD = "redirect:";

    /**
     *
     * 登录页面初始化..
     *
     * @param request
     * @param response
     * @return Page Template
     * @throws Exception
     */
    @RequestMapping(value = "/indexPageInit",method = RequestMethod.GET)
    public String indexPageInit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        return SystemContainer.SYSTEM_PAGES_TEMPLATE[0];
    }

    /**
     *
     * 主界面初始化..
     *
     * @param request
     * @param response
     * @return Page Template
     * @throws Exception
     */
    @RequestMapping(value = "/mainPageInit",method = RequestMethod.GET)
    public String mainPageInit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        return SystemContainer.SYSTEM_PAGES_TEMPLATE[1];
    }

    /**
     *
     * 标签界面初始化..
     *
     * @param request
     * @param response
     * @return Page Template
     * @throws Exception
     */
    @RequestMapping(value = "/tabPageInit",method = RequestMethod.GET)
    public String tabPageInit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        Dto rDto = (Dto)cooper4Reader.queryForObject("admin.core.Index.loadMenuRequest",super.getParamsAsDto(request));

        String mRequest = rDto.getAsString("mRequest");

        if(FredaUtils.isNotEmpty(mRequest) && mRequest.indexOf(".freda",mRequest.length()-6) != -1)
        {
            request.setAttribute(SystemContainer.SYSTEM_TAB_JS_CLS,mRequest);

            return SystemContainer.SYSTEM_PAGES_TEMPLATE[2];
        }
        return TO_CONTROLLER_HEAD + mRequest;
    }

    /**
     *
     * 我的工作台界面初始化..
     *
     * @param request
     * @param response
     * @return Page Template
     * @throws Exception
     */
    @RequestMapping(value = "/myBranchPageInit",method = RequestMethod.GET)
    public String myBranchPageInit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        return SystemContainer.SYSTEM_PAGES_TEMPLATE[3];
    }

    /**
     * 菜单加载.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/menuPanelInit",method = RequestMethod.GET)
    public String menuPanelInit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        super.write(JsonHelper.encodeObject2Json(indexService.getMenuItem(super.getParamsAsDto(request))),response);

        return null;
    }

    /**
     * 登录.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public String login(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        UserInfoVo user = indexService.queryUser(super.getParamsAsDto(request));

        String tips = "";

        if (FredaUtils.isEmpty(user))
        {
            tips = CoreContainer.CORE_LOGIN_FAIL_EMPTY;
        }
        else
        {
            if (user.getStatus() == 0)
            {
                tips = CoreContainer.CORE_LOGIN_FAIL_STATUS;
            }
            else
            {
                if (user.getLocked() == 1)
                {
                    tips = CoreContainer.CORE_LOGIN_FAIL_LOCKED;
                }
            }
        }
        if (FredaUtils.isEmpty(tips))
        {
            user.setIpAddr(WebUtils.getIpAddr(request));

            user.setExplorerType(FredaUtils.getClientExplorerType(request));

            super.setSessionAttribute(request,SystemContainer.SYSTEM_USER_INFO_VO_KEY,user);

            super.setOkTipMsg(CoreContainer.CORE_LOGIN_SUCCESS_TIPS,response);
        }
        else
        {
            super.setErrTipMsg(tips, response);
        }
        return null;
    }

    /**
     * 登出.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public String logout(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        super.removeSessionAttribute(request,SystemContainer.SYSTEM_USER_INFO_VO_KEY);

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 修改密码.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    public String changePwd(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        if (indexService.changePassWord(super.getParamsAsDto(request)))
        {
            super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);
        }
        else
        {
            super.setErrTipMsg(SystemContainer.TIPS_ERROR_MSG,response);
        }
        return null;
    }
}
