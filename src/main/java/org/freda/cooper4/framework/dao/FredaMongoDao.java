package org.freda.cooper4.framework.dao;

import com.mongodb.DBCollection;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 *
 * Mongo的使用Dao简单 查询操作请自行获取MongoTemplate进行查询
 *
 * Created by rally on 16/4/22.
 */
public interface FredaMongoDao
{
    /**
     * 删除
     * @param collectionName
     * @param query
     * @return count
     */
    public int delete(String collectionName, Query query);

    /**
     * 添加 支持单条插入
     * @param collectionName
     * @param t
     */
    public<T> void insert(String collectionName, T t);

    /**
     * 添加.支持批量插入
     * @param coolectionName
     * @param tList
     */
    public<T> void insertForList(String coolectionName, List<T> tList);

    /**
     * 修改..不会生成新的数据库..会更新多条
     * @param collectionName
     * @param update set条件
     * @param query where条件
     * @return count
     */
    public int update(String collectionName, Update update, Query query);

    /**
     * 获取指定集合
     * @param collectionName
     * @return
     */
    public DBCollection getCollection(String collectionName);

    /**
     * 创建集合
     * @param collectionName
     * @return 集合操作.
     */
    public DBCollection createCollection(String collectionName);

    /**
     * 条件是否在集合中
     * @param collectionName
     * @param query
     * @return
     */
    public boolean isExit4Find(String collectionName, Query query);

    /**
     *
     * 通用查询.
     *
     * @param collectionName
     * @param query
     * @param tClass
     * @return list
     */
    public<T> List<T> query(String collectionName,Query query,Class<T> tClass);

    /**
     * 在不Dao不满足的情况下..请自行拿取MongoTemplate完成复杂操作.
     * @return MongoTemplate
     */
    public MongoTemplate getMongoTemplate();
}
