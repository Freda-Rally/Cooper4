package org.freda.cooper4.framework.dao.impl;

import com.github.pagehelper.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.RowBounds;
import org.freda.cooper4.framework.dao.FredaReader;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.datastructure.ResultBean4Page;
import org.freda.cooper4.framework.datastructure.impl.BaseResultBean;
import org.freda.cooper4.framework.utils.FredaUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * 基础实现FredaReader中的方法..
 *
 *  系统内所有需要读取的Reader从中继承
 *
 * Created by rally on 16/4/22.
 */
public abstract class BaseReader extends SqlSessionDaoSupport implements FredaReader
{
    private static final Log log = LogFactory.getLog(BaseReader.class);

    /**
     * 查询一条记录
     *
     * @param statementName SQL语句ID号
     * @param parameterObject
     *            查询条件对象(map javaBean)
     */
    @Override
    public Object queryForObject(String statementName, Object parameterObject)
    {
        return super.getSqlSession().selectOne(statementName, parameterObject);
    }

    /**
     * 查询一条记录
     *
     * @param statementName SQL语句ID号
     */
    @Override
    public Object queryForObject(String statementName)
    {
        return super.getSqlSession().selectOne(statementName);
    }

    /**
     * 查询记录集合
     *
     * @param statementName SQL语句ID号
     * @param parameterObject
     *            查询条件对象(map javaBean)
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List queryForList(String statementName, Object parameterObject)
    {
        return super.getSqlSession().selectList(statementName, parameterObject);
    }

    /**
     * 按分页查询
     *
     * @param statementName SQL语句ID号
     * @param qDto
     *            查询条件对象(map javaBean)
     * @throws SQLException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public List queryForPage(String statementName, Dto qDto) throws SQLException
    {
        String start = qDto.getAsString("start");
        String limit = qDto.getAsString("limit");
        int startInt = 0;
        if (FredaUtils.isNotEmpty(start))
        {
            startInt = Integer.parseInt(start);
            qDto.put("start", startInt);
        }
        if (FredaUtils.isNotEmpty(limit))
        {
            int limitInt = Integer.parseInt(limit);
            qDto.put("end", limitInt);
        }

        Integer intStart = qDto.getAsInteger("start");
        Integer end = qDto.getAsInteger("end");
        if (FredaUtils.isEmpty(start) || FredaUtils.isEmpty(end))
        {
            try
            {
                throw new Exception("分页数据中,参数不足!");
            }
            catch (Exception e)
            {
                log.error("参数不足..导致出现异常!");
                e.printStackTrace();
            }
        }

        //PageHelper.startPage(intStart,end);

        return super.getSqlSession().selectList(statementName, qDto, new RowBounds(intStart.intValue(), end.intValue()));
    }
    /**
     * 分页查询 不使用分页插件.
     * @param statementName 结果SQL语句ID号
     * @param qDto 查询条件对象(map javaBean)
     * @return
     * @throws SQLException
     */

    @SuppressWarnings("unchecked")
    @Override
    public ResultBean4Page queryForPage2(String statementName, Dto qDto) throws SQLException
    {
        String page = qDto.getAsString("page");
        String limit = qDto.getAsString("limit");
        int startInt = 0;
        if (FredaUtils.isNotEmpty(limit))
        {
            int limitInt = Integer.parseInt(limit);
            qDto.put("end", limitInt);
        }
        if (FredaUtils.isNotEmpty(page))
        {
            startInt = (Integer.parseInt(page) - 1) * qDto.getAsInteger("end");
            qDto.put("start", startInt);
        }
        Integer intStart = qDto.getAsInteger("start");
        Integer end = qDto.getAsInteger("end");
        if (FredaUtils.isEmpty(page) || FredaUtils.isEmpty(limit))
        {
            try
            {
                throw new Exception("分页数据中,参数不足!");
            }
            catch (Exception e)
            {
                log.error("参数不足..导致出现异常!");
                e.printStackTrace();
            }
        }
        List<?> data = super.getSqlSession().selectList(statementName, qDto, new RowBounds(intStart.intValue(), end.intValue()));

        Long count = ((Page)data).getTotal();

        return new BaseResultBean(data , count.intValue() ,qDto.getAsInteger("page"),qDto.getAsInteger("limit"));
    }

    /**
     * 查询记录集合
     *
     * @param statementName SQL语句ID号
     */
    @SuppressWarnings("rawtypes")
    @Override
    public List queryForList(String statementName)
    {
        return super.getSqlSession().selectList(statementName);
    }

    /**
     * 获取Connection对象<br>
     * 说明：虽然向Dao消费端暴露了获取Connection对象的方法但不建议直接获取Connection对象进行JDBC操作
     *
     * @return 返回Connection对象
     * @throws SQLException
     */
    @Override
    public Connection getConnection() throws SQLException
    {
        return super.getSqlSession().getConnection();
    }
}
