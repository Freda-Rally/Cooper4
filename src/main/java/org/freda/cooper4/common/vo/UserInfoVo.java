package org.freda.cooper4.common.vo;

import org.freda.cooper4.framework.datastructure.impl.BaseVo;

/**
 *
 * Session中存储的登录用户信息.
 *
 * Created by rally on 16/4/30.
 */
public class UserInfoVo extends BaseVo
{
    /**
     * 用户ID
     */
    private long userId;
    /**
     * 部门ID
     */
    private long deptId;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 账号
     */
    private String account;
    /**
     * 是否锁定
     */
    private int locked;
    /**
     * 状态
     */
    private int status;
    /**
     * 用户类型
     */
    private int userType;
    /**
     * 登录IP
     */
    private String ipAddr;
    /**
     * 浏览器类型
     */
    private String explorerType;
    /**
     * 服务器SessionId
     */
    private String sessionId;


    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }

    public long getDeptId()
    {
        return deptId;
    }

    public void setDeptId(long deptId)
    {
        this.deptId = deptId;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getAccount()
    {
        return account;
    }

    public void setAccount(String account)
    {
        this.account = account;
    }

    public int getLocked()
    {
        return locked;
    }

    public void setLocked(int locked)
    {
        this.locked = locked;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(int status)
    {
        this.status = status;
    }

    public int getUserType()
    {
        return userType;
    }

    public void setUserType(int userType)
    {
        this.userType = userType;
    }

    public String getIpAddr()
    {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr)
    {
        this.ipAddr = ipAddr;
    }

    public String getExplorerType()
    {
        return explorerType;
    }

    public void setExplorerType(String explorerType)
    {
        this.explorerType = explorerType;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }
}
