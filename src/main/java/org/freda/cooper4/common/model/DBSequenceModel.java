package org.freda.cooper4.common.model;

/**
 *
 * ID Sequence读取Model
 *
 * Created by rally on 16/4/30.
 */
public class DBSequenceModel
{
    /**
     * 主键
     */
    private long sequenceId;
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 当前序号
     */
    private long sequence;
    /**
     * 格式.
     */
    private String pattern;
    /**
     * 类型.
     */
    private int type;
    /**
     * 备注
     */
    private String remark;


    public long getSequenceId()
    {
        return sequenceId;
    }

    public void setSequenceId(long sequenceId)
    {
        this.sequenceId = sequenceId;
    }

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

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
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
