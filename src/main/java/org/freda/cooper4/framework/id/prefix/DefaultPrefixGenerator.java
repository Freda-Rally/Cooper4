package org.freda.cooper4.framework.id.prefix;

import org.freda.cooper4.framework.id.PrefixGenerator;
import org.freda.cooper4.framework.id.exception.PrefixCreateException;
import org.freda.cooper4.framework.utils.DateUtils;

/**
 *
 * 前缀生成.
 *
 * Created by rally on 16/4/30.
 */
public class DefaultPrefixGenerator implements PrefixGenerator
{
    /**
     * 前缀.
     */
    private String prefix = "";
    /**
     * 是否需要加日期
     */
    private boolean withDate = false;
    /**
     * 日期格式.
     */
    private String datePattern = "yyyyMMdd";

    /**
     * 生成前缀.
     * @return
     * @throws PrefixCreateException
     */
    @Override
    public String create() throws PrefixCreateException
    {
        StringBuffer stringBuffer = new StringBuffer();

        stringBuffer.append(prefix);

        if (withDate)
        {
            stringBuffer.append(DateUtils.getTime(datePattern));
        }
        return stringBuffer.toString();
    }

    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

    public boolean isWithDate()
    {
        return withDate;
    }

    public void setWithDate(boolean withDate)
    {
        this.withDate = withDate;
    }

    public String getDatePattern()
    {
        return datePattern;
    }

    public void setDatePattern(String datePattern)
    {
        this.datePattern = datePattern;
    }
}
