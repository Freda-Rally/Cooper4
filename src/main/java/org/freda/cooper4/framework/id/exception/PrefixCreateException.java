package org.freda.cooper4.framework.id.exception;

/**
 *
 * 前缀生成异常
 *
 * Created by rally on 16/4/30.
 */
public class PrefixCreateException extends IDBaseException
{
    private static final String EXCEPTION_HEAD = "ID前缀创建异常:";

    public PrefixCreateException()
    {
        super("ID前缀创建异常!");
    }

    public PrefixCreateException(String message)
    {
        super(EXCEPTION_HEAD + message);
    }

    public PrefixCreateException(String message,Throwable throwable)
    {
        super(EXCEPTION_HEAD + message,throwable);
    }

    public PrefixCreateException(Throwable throwable)
    {
        super(throwable);
    }

}
