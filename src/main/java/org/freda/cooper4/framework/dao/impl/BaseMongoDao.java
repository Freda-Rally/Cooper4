package org.freda.cooper4.framework.dao.impl;

import com.mongodb.DBCollection;
import org.freda.cooper4.framework.dao.FredaMongoDao;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 *
 * Mongo Dao 本类只提供基本的增删改查..类似数据库的操作.
 *
 * Created by rally on 16/4/22.
 */
public abstract class BaseMongoDao implements FredaMongoDao
{
    private MongoTemplate mongoTemplate;

    /**
     * DELETE
     * @param collectionName
     * @param query
     * @return
     */
    @Override
    public int delete(String collectionName,Query query)
    {
        return mongoTemplate.remove(query,collectionName).getN();
    }

    /**
     * ADD
     * @param collectionName
     * @param t
     */
    @Override
    public<T> void insert(String collectionName, T t)
    {
        mongoTemplate.insert(t,collectionName);
    }

    /**
     * ADD FOR LIST
     * @param tList
     * @param coolectionName
     * @return
     */
    @Override
    public<T> void insertForList(String coolectionName, List<T> tList)
    {
        mongoTemplate.insert(tList,coolectionName);
    }

    /**
     * 批量EDIT
     * @param collectionName
     * @param query where条件
     * @param update set条件
     * @return
     */
    @Override
    public int update(String collectionName, Update update, Query query)
    {
        return mongoTemplate.updateMulti(query,update,collectionName).getN();
    }

    /**
     * 获取集合
     * @param collectionName
     * @return
     */
    @Override
    public DBCollection getCollection(String collectionName)
    {
        return mongoTemplate.getCollection(collectionName);
    }

    /**
     * 新建集合
     * @param collectionName
     * @return
     */
    @Override
    public synchronized DBCollection createCollection(String collectionName)
    {
        if(!mongoTemplate.collectionExists(collectionName))
        {
            return mongoTemplate.createCollection(collectionName);
        }
        return mongoTemplate.getCollection(collectionName);
    }

    /**
     * 查询是否存在
     * @param collectionName
     * @param query
     * @return
     */
    @Override
    public boolean isExit4Find(String collectionName, Query query)
    {
        return mongoTemplate.count(query,collectionName) > 0 ? true : false;
    }

    /**
     * set
     * @param mongoTemplate
     */
    public void setMongoTemplate(MongoTemplate mongoTemplate)
    {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * get Template 用于在基本的无法满足的时候使用.
     * @return MongoTemplate
     */
    @Override
    public MongoTemplate getMongoTemplate()
    {
        return this.mongoTemplate;
    }

    /**
     *
     * 通用查询.
     *
     * @param collectionName
     * @param query
     * @param tClass
     * @return list
     */
    public<T> List<T> query(String collectionName,Query query,Class<T> tClass)
    {
        return mongoTemplate.find(query,tClass,collectionName);
    }
}
