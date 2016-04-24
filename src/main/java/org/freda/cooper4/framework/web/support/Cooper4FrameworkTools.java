package org.freda.cooper4.framework.web.support;

import org.freda.cooper4.framework.dao.FredaDao;
import org.freda.cooper4.framework.dao.FredaMongoDao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 *
 * Cooper4 的工具集
 *
 * Created by rally on 16/4/23.
 */
@Component
public class Cooper4FrameworkTools
{
    @Resource(name = "cooper4MongoDao")
    private FredaMongoDao cooper4MongoDao;

    @Resource(name = "cooper4Dao")
    private FredaDao cooper4Dao;

    @Resource(name = "rabbitTemplate")
    private RabbitTemplate cooper4RabbitTemplate;

    @Resource(name = "redisTemplate")
    private RedisTemplate cooper4RedisTemplate;

    @Resource(name = "appEhCacheCacheManager")
    private EhCacheCacheManager cooper4EhCacheCacheManager;


    public FredaMongoDao getCooper4MongoDao()
    {
        return cooper4MongoDao;
    }

    public FredaDao getCooper4Dao()
    {
        return cooper4Dao;
    }

    public RabbitTemplate getCooper4RabbitTemplate()
    {
        return cooper4RabbitTemplate;
    }

    public RedisTemplate getCooper4RedisTemplate()
    {
        return cooper4RedisTemplate;
    }

    public EhCacheCacheManager getCooper4EhCacheCacheManager()
    {
        return cooper4EhCacheCacheManager;
    }
}
