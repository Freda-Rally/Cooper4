package org.freda.cooper4.framework.id.exception;

/**
 *
 * 序号创建异常.
 *
 * Created by rally on 16/4/29.
 */
public class SequenceCreateException extends IDBaseException
{
    private static final String EXCEPTION_HEAD = "序号创建异常:";

    public SequenceCreateException()
    {
        super("序号创建异常!");
    }

    public SequenceCreateException(String message)
    {
        super(EXCEPTION_HEAD + message);
    }

    public SequenceCreateException(String message,Throwable throwable)
    {
        super(EXCEPTION_HEAD + message,throwable);
    }

    public SequenceCreateException(Throwable throwable)
    {
        super(throwable);
    }
}
