package org.freda.cooper4.framework.datastructure;

import java.util.List;

/**
 *
 * 在不使用ExtJS的时候..此类提供分页支持
 *
 * Created by rally on 16/4/22.
 */
public interface ResultBean4Page
{
    public abstract int getStart();

    public abstract void setStart(int start);

    public abstract int getLimit();

    public abstract void setLimit(int limit);

    public abstract int getTotalCount();

    public abstract void setTotalCount(int totalCount);

    public abstract int getTotalPage();

    public abstract void setTotalPage(int totalPage);

    public abstract int getPage();

    public abstract void setPage(int page);

    public abstract Dto getoDto();

    public abstract void setoDto(Dto oDto);

    public abstract List<?> getData();

    public abstract void setData(List<?> data);

    public abstract String toJson(String dFormatString);
}
