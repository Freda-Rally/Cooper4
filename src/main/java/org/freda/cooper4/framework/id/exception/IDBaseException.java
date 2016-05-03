package org.freda.cooper4.framework.id.exception;

/**
 *
 * 所有ID的Exception父类
 *
 * Created by rally on 16/4/29.
 */
public abstract class IDBaseException extends RuntimeException
{
    public IDBaseException()
    {
        super("ID发生异常.");
    }

    public IDBaseException(String message,Throwable throwable)
    {
        super(message,throwable);
    }

    public IDBaseException(String message)
    {
        super(message);
    }

    public IDBaseException(Throwable throwable)
    {
        super(throwable);
    }
}
