package org.freda.cooper4.framework.id.store;

import org.freda.cooper4.framework.dao.FredaDao;
import org.freda.cooper4.framework.id.SequenceStore;
import org.freda.cooper4.framework.id.exception.SequenceStoreException;

/**
 *
 * 数据库存储.
 *
 * Created by rally on 16/4/30.
 */
public class DBSequenceStore implements SequenceStore
{
    private String tableName;//ID存储的DB表

    private String idColName;//ID标识字段.

    private String valueColName;//ID数值字段.

    private FredaDao fredaDao;//数据库存储工具.

    /**
     * 更新数据库.
     * @param pSequence
     * @param pSequenceId
     * @throws SequenceStoreException
     */
    @Override
    public void update(long pSequence, String pSequenceId) throws SequenceStoreException
    {

    }

    /**
     * 从数据库中加载.
     * @param pSequenceId
     * @throws SequenceStoreException
     */
    @Override
    public long load(String pSequenceId) throws SequenceStoreException
    {
        return 0;
    }

    public String getTableName()
    {
        return tableName;
    }

    public void setTableName(String tableName)
    {
        this.tableName = tableName;
    }

    public String getIdColName()
    {
        return idColName;
    }

    public void setIdColName(String idColName)
    {
        this.idColName = idColName;
    }

    public String getValueColName()
    {
        return valueColName;
    }

    public void setValueColName(String valueColName)
    {
        this.valueColName = valueColName;
    }


    public FredaDao getFredaDao()
    {
        return fredaDao;
    }

    public void setFredaDao(FredaDao fredaDao)
    {
        this.fredaDao = fredaDao;
    }
}
