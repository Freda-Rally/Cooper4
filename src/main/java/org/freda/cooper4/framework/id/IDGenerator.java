package org.freda.cooper4.framework.id;

import org.freda.cooper4.framework.id.exception.IDBaseException;

/**
 *
 * ID发生器接口.
 *
 * Created by rally on 16/4/30.
 */
public interface IDGenerator
{
    /**
     *
     * 生成ID
     *
     * @return ID
     * @throws IDBaseException
     */
    public String create(String id) throws IDBaseException;
}
