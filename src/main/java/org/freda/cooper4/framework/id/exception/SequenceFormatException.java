package org.freda.cooper4.framework.id.exception;

/**
 *
 * ID格式化异常.
 *
 * Created by rally on 16/4/29.
 */
public class SequenceFormatException extends IDBaseException
{
    private static final String EXCEPTION_HEAD = "格式化序号异常:";

    public SequenceFormatException()
    {
        super("格式化序号异常!");
    }

    public SequenceFormatException(String message)
    {
        super(EXCEPTION_HEAD + message);
    }

    public SequenceFormatException(String message,Throwable throwable)
    {
        super(EXCEPTION_HEAD + message,throwable);
    }

    public SequenceFormatException(Throwable throwable)
    {
        super(throwable);
    }
}
