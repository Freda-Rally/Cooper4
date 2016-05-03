package org.freda.cooper4.framework.id.store.model;

/**
 *
 * DB或Ehcache中存储的model
 *
 * Created by rally on 16/4/30.
 */
public class DefaultStoreModel
{
    /**
     * 字段名称
     */
    private String fieldName;
    /**
     * 序号(当前最大)
     */
    private long sequence;
    /**
     * 格式化.
     */
    private String pattern;

    public String getFieldName()
    {
        return fieldName;
    }

    public void setFieldName(String fieldName)
    {
        this.fieldName = fieldName;
    }

    public long getSequence()
    {
        return sequence;
    }

    public void setSequence(long sequence)
    {
        this.sequence = sequence;
    }

    public String getPattern()
    {
        return pattern;
    }

    public void setPattern(String pattern)
    {
        this.pattern = pattern;
    }
}
