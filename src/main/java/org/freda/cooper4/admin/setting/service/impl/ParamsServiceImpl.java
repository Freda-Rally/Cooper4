package org.freda.cooper4.admin.setting.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.admin.setting.service.ParamsManagerService;
import org.freda.cooper4.common.service.ParamsInitService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.common.utils.CommonContainer;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.datastructure.impl.BaseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import java.util.List;

/**
 *
 * PARAMS SERVICE
 *
 * Created by rally on 16/5/4.
 */
@Service
public class ParamsServiceImpl extends Cooper4AdminBaseServiceImpl implements ParamsManagerService,ParamsInitService
{
    private static final Log log = LogFactory.getLog(ParamsServiceImpl.class);

    /**
     * 初始化所有参数放入Application中.
     *
     * @param context
     */
    @Override
    public void init(ServletContext context)
    {
        List<Dto> paramsList = super.getDao().queryForList("admin.setting.Params.queryAll4Init");

        Dto contextDto = new BaseDto();

        log.info("系统全局参数初始化中.");
        log.info("==============================================================");
        log.info("|KEY           |VALUE           |CODE描述" );

        for (Dto param : paramsList)
        {
            log.info("|" + param.getAsString("pKey") + "    |" + param.getAsString("pValue") +
                    "   |" + param.getAsString("pDesc"));

            contextDto.put(param.getAsString("pKey"),param.getAsString("pValue"));
        }
        context.setAttribute(CommonContainer.APPLICATION_PARAMS_NAME,context);

        log.info("==============================================================");
        log.info("系统全局参数初始化成功..");
    }

    /**
     * 添加
     *
     * @param pDto
     */
    @Override
    public void add(Dto pDto)
    {
        super.getDao().insert("",pDto);
    }

    /**
     * 修改
     *
     * @param pDto
     */
    @Override
    public void edit(Dto pDto)
    {
        super.getDao().update("",pDto);
    }

    /**
     * 删除
     *
     * @param pDto
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Dto pDto)
    {
        String[] ids = pDto.getAsString("ids").split(",");

        for (String id : ids)
        {
            pDto.put("id",id);

            super.getDao().delete("",pDto);
        }
    }
}
