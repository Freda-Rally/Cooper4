package org.freda.cooper4.common.support.web;

import org.freda.cooper4.common.vo.UserInfoVo;
import org.freda.cooper4.framework.dao.FredaDao;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.datastructure.ParamsDto;
import org.freda.cooper4.framework.web.support.BaseService;

import javax.annotation.Resource;

/**
 *
 * Cooper Base Service
 *
 * Created by rally on 16/5/2.
 */
public class Cooper4AdminBaseServiceImpl extends BaseService
{
    @Resource
    private Cooper4FrameworkTools cooper4FrameworkTools;

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

    protected FredaDao getDao()
    {
        return cooper4FrameworkTools.getCooper4Dao();
    }
}
