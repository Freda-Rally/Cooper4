package org.freda.cooper4.framework.id.exception;

/**
 *
 * ID 存储异常.
 *
 * Created by rally on 16/4/30.
 */
public class SequenceStoreException extends IDBaseException
{
    private static final String EXCEPTION_HEAD = "序号存储异常:";

    public SequenceStoreException()
    {
        super("存储触发需要异常.");
    }

    public SequenceStoreException(String message, Throwable cause)
    {
        super(EXCEPTION_HEAD + message, cause);
    }

    public SequenceStoreException(String message)
    {
        super(EXCEPTION_HEAD + message);
    }

    public SequenceStoreException(Throwable cause)
    {
        super(cause);
    }
}
