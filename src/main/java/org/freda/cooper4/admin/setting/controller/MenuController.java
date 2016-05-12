package org.freda.cooper4.admin.setting.controller;

import com.github.pagehelper.Page;
import org.freda.cooper4.admin.setting.service.MenuManagerService;
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
 * 菜单资源管理.
 *
 * Created by rally on 16/5/10.
 */
@Controller
@RequestMapping(value = "/menu")
public class MenuController extends Cooper4AdminBaseController
{
    @Resource
    private MenuManagerService menuManagerService;

    /**
     * 查询.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list4Page")
    public String list4Page(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Page page = cooper4Reader.queryForPage("admin.setting.Menu.list4Page",super.getParamsAsDto(request));

        super.write(JsonHelper.encodeList2PageJson(page.getResult(),page.getTotal(),SystemContainer.DATE_TIME_FORMART[0]),response);

        return null;
    }

    /**
     * 新增
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        menuManagerService.add(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 修改.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        menuManagerService.edit(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 删除.
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        menuManagerService.delete(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 树
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/menuTreeInit")
    public String menuTreeInit(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        super.write(JsonHelper.encodeObject2Json(menuManagerService.menuTreeInit(super.getParamsAsDto(request))),response);

        return null;
    }
}
