package org.freda.cooper4.framework.id;

import org.freda.cooper4.framework.id.exception.SequenceStoreException;

/**
 *
 * 所有存储并触发的Store接口
 *
 * Created by rally on 16/4/30.
 */
public interface SequenceStore
{
    /**
     * 更新.
     * @param pSequence
     * @param pSequenceId
     * @throws SequenceStoreException
     */
    public void update(long pSequence,final String pSequenceId) throws SequenceStoreException;

    /**
     * 加载当前最大.
     * @param pSequenceId
     * @throws SequenceStoreException
     */
    public long load(final String pSequenceId) throws SequenceStoreException;

}
