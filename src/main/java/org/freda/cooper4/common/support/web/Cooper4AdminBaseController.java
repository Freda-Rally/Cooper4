package org.freda.cooper4.common.support.web;

import org.freda.cooper4.common.vo.UserInfoVo;
import org.freda.cooper4.framework.dao.FredaDao;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.datastructure.ParamsDto;
import org.freda.cooper4.framework.web.support.BaseController;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Cooper4 Admin Base Controller
 *
 * Created by rally on 16/4/25.
 */
public abstract class Cooper4AdminBaseController extends BaseController
{
    /**
     * 可重写..可增加部分Cooper4系统中 Controller里面需要的必要方法..
     */

    @Autowired
    protected FredaDao cooper4Dao;

    /**
     * 获取当前用户.
     * @param pDto
     * @return
     */
    protected UserInfoVo getUserInfoVo(Dto pDto)
    {
        ParamsDto paramsDto = (ParamsDto)pDto;

        if (paramsDto.getRequest() != null)
        {
            return paramsDto.getSystemUserVo(UserInfoVo.class);
        }
        return null;
    }
}
