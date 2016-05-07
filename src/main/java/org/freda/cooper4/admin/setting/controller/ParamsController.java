package org.freda.cooper4.admin.setting.controller;

import com.github.pagehelper.Page;
import org.freda.cooper4.admin.setting.service.ParamsManagerService;
import org.freda.cooper4.common.service.ParamsInitService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseController;
import org.freda.cooper4.framework.json.JsonHelper;
import org.freda.cooper4.framework.utils.SystemContainer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * 系统参数.
 *
 * Created by rally on 16/5/5.
 */
@Controller
@RequestMapping("/params")
public class ParamsController extends Cooper4AdminBaseController
{

    private ParamsManagerService paramsManagerService;
    /**
     * 分页查询参数.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/list4Page")
    public String list4Page(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Page page = (Page)cooper4Reader.queryForPage("admin.setting.Params.list4Page",super.getParamsAsDto(request));

        super.write(JsonHelper.encodeList2PageJson(page.getResult(),page.getTotal()),response);

        return null;
    }

    /**
     * 添加
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/add")
    public String add(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        paramsManagerService.add(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 修改
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/edit")
    public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        paramsManagerService.edit(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 删除
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/delete")
    public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        paramsManagerService.delete(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 同步
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/syn2ACache")
    public String syn2ACache(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        ParamsInitService paramsInitService = (ParamsInitService)paramsManagerService;

        paramsInitService.init(request.getServletContext());

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }
}
