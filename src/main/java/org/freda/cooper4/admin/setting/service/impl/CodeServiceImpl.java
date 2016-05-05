package org.freda.cooper4.admin.setting.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.admin.setting.service.CodeManagerService;
import org.freda.cooper4.common.service.CodeInitService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.common.utils.CommonContainer;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.json.JsonHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * CODE SERVICE
 *
 * Created by rally on 16/5/4.
 */
@Service
public class CodeServiceImpl extends Cooper4AdminBaseServiceImpl implements CodeManagerService,CodeInitService
{

    private static final Log log = LogFactory.getLog(CodeServiceImpl.class);

    /**
     * 初始化系统数据字典.将字典参数放入EhCache.
     */
    @Override
    public void init()
    {
        List<Dto> codeList = super.getDao().queryForList("admin.setting.Code.queryAll4Init");

        log.info("系统数据字典初始化中.");
        log.info("==============================================================");
        log.info("|字段名           |字段描述           |CODE值          |CODE描述" );

        for (Dto code : codeList)
        {
            log.info("|" + code.getAsString("field") + "    |" + code.getAsString("fieldName") +
                    "   |" + code.getAsString("codeValue") + "  |" + code.getAsString("codeDesc"));
        }
        String json = JsonHelper.encodeObject2Json(codeList);
        //缓存整体JSON为前台使用.
        super.getTools().getCooper4EhCacheCacheManager().getCache(CommonContainer.CACHE_CODE_NAME).put(CommonContainer.CACHE_CODE_KEY,json);

        log.info("==============================================================");
        log.info("系统数据字典初始化完成..");
    }

    /**
     * 销毁
     */
    @Override
    public void destroyed()
    {
        //清除缓存
        super.getTools().getCooper4EhCacheCacheManager().getCache(CommonContainer.CACHE_CODE_NAME).clear();
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
