package org.freda.cooper4.admin.setting.controller;

import com.github.pagehelper.Page;
import org.freda.cooper4.admin.setting.service.CodeManagerService;
import org.freda.cooper4.common.service.CodeInitService;
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
 * 数据字典Controller.
 *
 * Created by rally on 16/5/5.
 */
@Controller
@RequestMapping(value = "/code")
public class CodeController extends Cooper4AdminBaseController
{
    @Resource
    private CodeManagerService codeManagerService;

    /**
     * 分页查询
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list4Page")
    public String list4Page(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        Page page = (Page) cooper4Reader.queryForPage("admin.setting.Code.query4Page",super.getParamsAsDto(request));

        super.write(JsonHelper.encodeList2PageJson(page.getResult(),page.getTotal(),SystemContainer.DATE_TIME_FORMART[0]),response);

        return null;
    }

    /**
     * 添加
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        codeManagerService.add(super.getParamsAsDto(request));

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
    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        codeManagerService.edit(super.getParamsAsDto(request));

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
    @RequestMapping(value = "/delete")
    public String delete(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        codeManagerService.delete(super.getParamsAsDto(request));

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }

    /**
     * 将Code重新同步至EhCache中.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/synToCache")
    public String synToCache(HttpServletRequest request,HttpServletResponse response) throws Exception
    {
        CodeInitService codeInitService = (CodeInitService) codeManagerService;

        codeInitService.reInit();

        super.setOkTipMsg(SystemContainer.TIPS_SUCCESS_MSG,response);

        return null;
    }
}
