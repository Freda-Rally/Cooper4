package org.freda.cooper4.framework.id.store;

import org.freda.cooper4.framework.id.SequenceStore;
import org.freda.cooper4.framework.id.exception.SequenceStoreException;

/**
 *
 * EHCACHE Store
 *
 * Created by rally on 16/4/30.
 */
public class EhCacheSequenceStore implements SequenceStore
{

    @Override
    public void update(long pSequence, String pSequenceId) throws SequenceStoreException
    {

    }

    @Override
    public long load(String pSequenceId) throws SequenceStoreException
    {
        return 0;
    }
}
