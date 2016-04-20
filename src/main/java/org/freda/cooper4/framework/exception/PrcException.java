package org.freda.cooper4.framework.exception;

/**
 *
 * 存储过程Exception
 *
 * Created by rally on 16-4-12.
 */
public class PrcException extends RuntimeException
{
    /**
     *
     */
    private static final long serialVersionUID = 586793019958436013L;
    private String appCode;
    private String errorMsg;
    private String prcName;

    public PrcException(String prcName, String errorMsg)
    {
        super("调用存储过程[" + prcName
                + "]发生错误,错误原因：[" + errorMsg + "]");
        setErrorMsg(errorMsg);
    }

    public PrcException(String prcName, String appCode, String errorMsg)
    {
        super("调用存储过程[" + prcName
                + "]发生错误,错误编码为：[" + appCode + "] 错误原因：[" + errorMsg + "]");
        setAppCode(appCode);
        setPrcName(prcName);
        setErrorMsg(errorMsg);
    }

    public String getAppCode()
    {
        return appCode;
    }

    public void setAppCode(String appCode)
    {
        this.appCode = appCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg)
    {
        this.errorMsg = errorMsg;
    }

    public String getPrcName()
    {
        return prcName;
    }

    public void setPrcName(String prcName)
    {
        this.prcName = prcName;
    }
}
