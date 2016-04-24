package org.freda.cooper4.framework.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.freda.cooper4.framework.dao.FredaMongoDao;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.lang.reflect.Field;
import java.util.List;

/**
 *
 * Mongo Dao
 *
 * Created by rally on 16/4/22.
 */
public abstract class BaseMongoDao implements FredaMongoDao
{
    private MongoTemplate mongoTemplate;

    /**
     * DELETE
     * @param collectionName
     * @param doc
     * @return
     */
    @Override
    public int delete(String collectionName, DBObject doc)
    {
        return mongoTemplate.getCollection(collectionName).remove(doc).getN();
    }

    /**
     * ADD
     * @param collectionName
     * @param doc
     * @return
     */
    @Override
    public int insert(String collectionName, DBObject doc)
    {
        return mongoTemplate.getCollection(collectionName).save(doc).getN();
    }

    /**
     * ADD FOR LIST
     * @param coolectionName
     * @param docs
     * @return
     */
    @Override
    public int insertForList(String coolectionName, List<DBObject> docs)
    {
        return mongoTemplate.getCollection(coolectionName).insert(docs).getN();
    }

    /**
     * EDIT
     * @param collectionName
     * @param q where条件
     * @param o set条件
     * @return
     */
    @Override
    public int update(String collectionName, DBObject q, DBObject o)
    {
        return mongoTemplate.getCollection(collectionName).update(q, o, false, false).getN();
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
     * @param indexCol
     * @return
     */
    @Override
    public synchronized DBCollection createCollection(String collectionName,String indexCol)
    {
        if(!mongoTemplate.collectionExists(collectionName))
        {
            mongoTemplate.createCollection(collectionName);
        }
        this.getCollection(collectionName).createIndex(indexCol);

        return this.getCollection(collectionName);
    }

    /**
     * 查询是否存在
     * @param collectionName
     * @param q
     * @return
     */
    @Override
    public boolean isExit4Find(String collectionName, DBObject q)
    {
        return mongoTemplate.getCollection(collectionName).count(q) > 0 ? true : false;
    }

    /**
     * 将Model转化为 DBObject
     * @param model
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    @Override
    public DBObject setModel2DbObject(final Object model) throws IllegalArgumentException, IllegalAccessException
    {
        DBObject obj = new BasicDBObject();
        //反转机制设置进DBObject
        Field[] fields = model.getClass().getDeclaredFields();

        if(fields != null)
        {
            for(Field field : fields)
            {
                field.setAccessible(true);

                obj.put(field.getName(), field.get(model));
            }
        }
        else
        {
            return null;
        }
        return obj;
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
     * get
     * @return MongoTemplate
     */
    @Override
    public MongoTemplate getMongoTemplate()
    {
        return this.mongoTemplate;
    }
}
