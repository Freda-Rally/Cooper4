package org.freda.cooper4.common.generator.dbid;

import org.freda.cooper4.common.model.DBSequenceModel;
import org.freda.cooper4.common.utils.CommonContainer;
import org.freda.cooper4.framework.dao.FredaDao;
import org.freda.cooper4.framework.id.SequenceStore;
import org.freda.cooper4.framework.id.exception.SequenceStoreException;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * 系统内部自己的存储.
 * load从Ehcache中拿取.
 * update更新Ehcache与DB
 *
 * Created by rally on 16/4/30.
 */
@Component(value = "cooper4SequenceStore")
public class Cooper4SequenceStore implements SequenceStore
{
    private String tableName;//ID存储的DB表.

    private String idColName;//ID标识字段.

    private String valueColName;//ID数值字段.

    @Resource(name = "cooper4Dao")
    private FredaDao cooper4Dao;//Dao.

    @Resource(name = "appEhCacheCacheManager")
    private EhCacheCacheManager cooper4EhCacheCacheManager;//EhCache

    @Override
    public void update(long pSequence, String pSequenceId) throws SequenceStoreException
    {
        //更新EhCache
        DBSequenceModel sequenceModel = cooper4EhCacheCacheManager.getCache(CommonContainer.CACHE_DB_ID_NAME).get(pSequenceId,DBSequenceModel.class);
        sequenceModel.setSequence(pSequence);
        cooper4EhCacheCacheManager.getCache(CommonContainer.CACHE_DB_ID_NAME).put(pSequenceId,sequenceModel);
        //更新DB
        cooper4Dao.update("Common.DBIDSequence.editSequence",sequenceModel);
    }

    @Override
    public long load(String pSequenceId) throws SequenceStoreException
    {
        return cooper4EhCacheCacheManager.getCache(CommonContainer.CACHE_DB_ID_NAME)
                .get(pSequenceId, DBSequenceModel.class).getSequence();
    }
}
