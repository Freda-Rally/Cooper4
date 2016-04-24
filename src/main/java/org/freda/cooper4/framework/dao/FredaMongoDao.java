package org.freda.cooper4.framework.dao;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoTemplate;

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
     * @param doc
     * @return
     */
    public int delete(String collectionName, DBObject doc);

    /**
     * 添加 支持单条插入
     * @param collectionName
     * @param doc
     * @return
     */
    public int insert(String collectionName, DBObject doc);

    /**
     * 添加.支持批量插入
     * @param coolectionName
     * @param docs
     * @return
     */
    public int insertForList(String coolectionName, List<DBObject> docs);

    /**
     * 修改..不会生成新的数据库..会更新多条
     * @param collectionName
     * @param q where条件
     * @param o set条件
     * @return
     */
    public int update(String collectionName, DBObject q, DBObject o);

    /**
     * 获取指定集合
     * @param collectionName
     * @return
     */
    public DBCollection getCollection(String collectionName);

    /**
     * 创建集合
     * @param collectionName
     * @return
     */
    public DBCollection createCollection(String collectionName,String indeCol);

    /**
     * 条件是否在集合中
     * @param collectionName
     * @param q
     * @return
     */
    public boolean isExit4Find(String collectionName, DBObject q);

    /**
     * 设置添加时的数据
     * @param model
     * @param c
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public DBObject setModel2DbObject(Object model,Class<?> c) throws IllegalArgumentException, IllegalAccessException;

    /**
     * 在不Dao不满足的情况下..请自行拿取MongoTemplate完成复杂操作.
     * @return
     */
    public MongoTemplate getMongoTemplate();
}
