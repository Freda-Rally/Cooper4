package org.freda.cooper4.admin.setting.controller;

import com.github.pagehelper.Page;
import org.freda.cooper4.admin.setting.utils.SettingContainer;
import org.freda.cooper4.admin.setting.service.DeptTreeService;
import org.freda.cooper4.admin.setting.service.OrganizationService;
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
 * 人员.部门.角色的管理Controller.
 *
 * Created by rally on 16/5/8.
 */
@Controller
@RequestMapping(value = "/organization")
public class OrganizationController extends Cooper4AdminBaseController
{
    @Resource
    private OrganizationService organizationService;

    @Resource
    private DeptTreeService deptTreeService;
    /**
     *
     * 人员查询.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userList4Page")
    public String userList4Page(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        Page page = cooper4Reader.queryForPage("admin.setting.Organization.userList4Page",super.getParamsAsDto(request));

        super.write(JsonHelper.encodeList2PageJson(page.getResult(),page.getTotal()),response);

        return null;
    }

    /**
     * 加载用户信息
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userLoad")
    public String userLoad(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        super.write(JsonHelper.encodeObject2Json(cooper4Reader.queryForObject("admin.setting.Organization.loadUser",super.getParamsAsDto(request))),response);

        return null;
    }

    /**
     *
     * 人员添加
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userAdd")
    public String userAdd(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        if (organizationService.userAdd(super.getParamsAsDto(request)))

            super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);
        else
            super.setErrTipMsg(SettingContainer.TIPS_ADD_USER_ACCOUNT,response);

        return null;
    }

    /**
     *
     * 人员修改.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userEdit")
    public String userEdit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        organizationService.userEdit(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     *
     * 人员删除.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userDelete")
    public String userDelete(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        organizationService.userDelete(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * ========================
     */

    /**
     *
     * 部门查询.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deptList4Page")
    public String deptList4Page(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        Page page = cooper4Reader.queryForPage("admin.setting.Organization.deptList4Page",super.getParamsAsDto(request));

        super.write(JsonHelper.encodeList2PageJson(page.getResult(),page.getTotal()),response);

        return null;
    }

    /**
     * 部门添加.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deptAdd")
    public String deptAdd(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        organizationService.deptAdd(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 部门修改.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deptEdit")
    public String deptEdit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        organizationService.deptEdit(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 部门删除.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deptDelete")
    public String deptDelete(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        organizationService.deptDelete(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * ========================
     */
    /**
     * 角色查询.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/roleList4Page")
    public String roleList4Page(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        Page page = cooper4Reader.queryForPage("admin.setting.Organization.roleList4Page",super.getParamsAsDto(request));

        super.write(JsonHelper.encodeList2PageJson(page.getResult(),page.getTotal(),SystemContainer.DATE_TIME_FORMART[0]),response);

        return null;
    }

    /**
     * 角色添加.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/roleAdd")
    public String roleAdd(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        organizationService.roleAdd(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 角色修改.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/roleEdit")
    public String roleEdit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        organizationService.roleEdit(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 角色删除.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/roleDelete")
    public String roleDelete(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        organizationService.roleDelete(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 部门树.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deptTreeInit")
    public String deptTreeInit(HttpServletRequest request,HttpServletResponse response)throws Exception
    {
        super.write(JsonHelper.encodeObject2Json(deptTreeService.treeInit(super.getParamsAsDto(request))),response);

        return null;
    }
}
