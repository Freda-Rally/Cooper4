package org.freda.cooper4.common.generator.dbid;

import org.freda.cooper4.framework.utils.SpringBeanLoader;

/**
 *
 * 获得数据库表ID
 *
 * Created by rally on 16/4/30.
 */
public class Cooper4DBIdHelper
{
    /**
     *
     * 获取相应数据库表的ID.
     *
     * @param colName
     * @return
     */
    public static long getDbTableID(String colName)
    {
        return Cooper4SequenceGenerator.getInstance().setSequenceStore(
                        SpringBeanLoader.getSpringBean(
                                "cooper4SequenceStore",Cooper4SequenceStore.class)).next(colName);
    }
}
