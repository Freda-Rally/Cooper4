package org.freda.cooper4.framework.id;

import org.freda.cooper4.framework.id.exception.PrefixCreateException;

/**
 * Created by rally on 16/4/30.
 */
public interface PrefixGenerator
{
    /**
     * 创建前缀
     * @return
     * @throws PrefixCreateException
     */
    public String create() throws PrefixCreateException;
}
