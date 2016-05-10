package org.freda.cooper4.admin.setting.controller;

import org.freda.cooper4.admin.setting.service.Authority4OrganizationService;
import org.freda.cooper4.admin.setting.service.Authority4TreeService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseController;
import org.freda.cooper4.framework.json.JsonHelper;
import org.freda.cooper4.framework.utils.SystemContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 权限设置.
 *
 * Created by rally on 16/5/9.
 */
@Controller
@RequestMapping(value = "/authority")
public class AuthorityController extends Cooper4AdminBaseController
{
    @Resource
    private Authority4TreeService authority4TreeService;

    @Resource
    private Authority4OrganizationService authority4OrganizationService;
    /**
     * 部门人员树..用于角色绑定用户.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userAndDeptTreeWithRoleAuth")
    public String userAndDeptTreeWithRoleAuth(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        super.write(JsonHelper.encodeObject2Json(authority4TreeService.userAndDeptTree4Role(super.getParamsAsDto(request))),response);

        return null;
    }

    /**
     * 角色树..用于用户绑定角色.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/roleTreeWithUserAuth")
    public String roleTreeWithUserAuth(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        super.write(JsonHelper.encodeObject2Json(authority4TreeService.roleTree4User(super.getParamsAsDto(request))),response);

        return null;
    }

    /**
     * 菜单树..用于角色绑定菜单..
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/menuTreeWithRoleAuth")
    public String menuTreeWithRoleAuth(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        super.write(JsonHelper.encodeObject2Json(authority4TreeService.menuTree4Role(super.getParamsAsDto(request))),response);

        return null;
    }

    /**
     * 用户绑定至角色.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/user2Role")
    public String user2Role(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        authority4OrganizationService.user2Role(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 角色绑定至用户.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/role2User")
    public String role2User(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        authority4OrganizationService.role2User(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 菜单绑定至角色.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/menu2Role")
    public String menu2Role(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        authority4OrganizationService.menu2Role(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }
}
