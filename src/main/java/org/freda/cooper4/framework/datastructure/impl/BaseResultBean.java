package org.freda.cooper4.framework.datastructure.impl;

import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.datastructure.ResultBean4Page;
import org.freda.cooper4.framework.json.JsonHelper;

import java.util.List;

/**
 *
 *页面专用:返回分页DATA BEAN
 *
 * Created by rally on 16/4/22.
 */
public class BaseResultBean implements ResultBean4Page
{
    private static final int DEF_LIMIT = 20;
    /**
     * 开始条数
     */
    private int start;
    /**
     * 每页长度
     */
    private int limit;
    /**
     * 总条数
     */
    private int totalCount;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int page;
    /**
     * 附加参数
     */
    private Dto oDto;
    /**
     * 数据
     */
    private List<?> data;

    public BaseResultBean()
    {
        this.limit = this.getDefLimit();

        this.start = 0;

        this.page = 1;

        this.totalCount = 0;

        this.totalPage = 0;

        this.data = null;
    }

    public BaseResultBean(List<?> data , int totalCount , int sPage , int sLimit)
    {
        this.data = data;

        this.totalCount = totalCount;

        if(sLimit <= 0)
        {
            this.limit = this.getDefLimit();
        }
        else
        {
            this.limit = sLimit;
        }
        if(sPage <= 0)
        {
            this.page = 1;
        }
        else
        {
            this.page = sPage;
        }
        if(totalCount <= 0)
        {
            this.totalPage = 0;
        }
        else
        {
            this.totalPage = totalCount % this.limit == 0 ? this.totalCount / this.limit : this.totalCount / this.limit + 1;
        }
        this.start = (sPage - 1) * this.limit;
    }

    private int getDefLimit()
    {
        return DEF_LIMIT;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#getStart()
     */
    @Override
    public int getStart()
    {
        return start;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#setStart(int)
     */
    @Override
    public void setStart(int start)
    {
        this.start = start;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#getLimit()
     */
    @Override
    public int getLimit()
    {
        return limit;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#setLimit(int)
     */
    @Override
    public void setLimit(int limit)
    {
        this.limit = limit;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#getTotalCount()
     */
    @Override
    public int getTotalCount()
    {
        return totalCount;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#setTotalCount(int)
     */
    @Override
    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
        if (totalCount > 0)
            this.totalPage = totalCount % limit == 0 ? totalCount / limit : totalCount / limit + 1;
        else
            this.totalPage = 0;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#getTotalPage()
     */
    @Override
    public int getTotalPage()
    {
        return totalPage;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#setTotalPage(int)
     */
    @Override
    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#getPage()
     */
    @Override
    public int getPage()
    {
        return page;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#setPage(int)
     */
    @Override
    public void setPage(int page)
    {
        this.page = page;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#getoDto()
     */
    @Override
    public Dto getoDto()
    {
        return oDto;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#setoDto(com.rjkx.sk.system.datastructure.Dto)
     */
    @Override
    public void setoDto(Dto oDto)
    {
        this.oDto = oDto;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#getData()
     */
    @Override
    public List<?> getData()
    {
        return data;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#setData(java.util.List)
     */
    @Override
    public void setData(List<?> data)
    {
        this.data = data;
    }

    /* (non-Javadoc)
     * @see com.rjkx.sk.system.datastructure.impl.ResultDataBean#toJson(java.lang.String)
     */
    @Override
    public String toJson(String dFormatString)
    {
        if(this.getTotalCount() >= 0)
        {
            return JsonHelper.encodeList2PageJson(this.getData(), new Long(this.getTotalCount()), dFormatString);
        }
        else
        {
            return null;
        }
    }
}
