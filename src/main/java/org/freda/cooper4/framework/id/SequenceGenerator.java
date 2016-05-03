package org.freda.cooper4.framework.id;

import org.freda.cooper4.framework.id.exception.SequenceCreateException;

/**
 *
 * 顺序发生器接口..所有发生器实现该接口.
 *
 * Created by rally on 16/4/29.
 */
public interface SequenceGenerator
{
    /**
     *
     * 获取下一个
     *
     * @return 下一个
     * @throws SequenceCreateException
     */
    public abstract long next(String id)throws SequenceCreateException;
}
