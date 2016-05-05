package org.freda.cooper4.admin.setting.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.common.model.DBSequenceModel;
import org.freda.cooper4.common.service.IDSequenceInitService;
import org.freda.cooper4.common.support.web.Cooper4AdminBaseServiceImpl;
import org.freda.cooper4.common.utils.CommonContainer;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 * ID SEQUENCE SERVICE
 *
 * Created by rally on 16/5/4.
 */
@Service
public class IDSequenceServiceImpl extends Cooper4AdminBaseServiceImpl implements IDSequenceInitService
{
    private static final Log log = LogFactory.getLog(IDSequenceServiceImpl.class);

    /**
     * 初始化.将序列放入EhCache.
     */
    @Override
    public void init()
    {
        List<DBSequenceModel> sequenceList = super.getDao().queryForList("common.DBIDSequence.loadAllSequence");

        for (DBSequenceModel sequence : sequenceList)
        {
            super.getTools().getCooper4EhCacheCacheManager().getCache(CommonContainer.CACHE_DB_ID_NAME).put(sequence.getSequenceId(),sequence);
        }
        log.info("数据库表主键ID Store加载成功..");
    }
}
