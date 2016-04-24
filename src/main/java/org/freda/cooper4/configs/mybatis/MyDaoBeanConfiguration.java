package org.freda.cooper4.configs.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.freda.cooper4.configs.mongodb.MongoConfiguration;
import org.freda.cooper4.framework.dao.FredaDao;
import org.freda.cooper4.framework.dao.FredaMongoDao;
import org.freda.cooper4.framework.dao.FredaReader;
import org.freda.cooper4.framework.dao.impl.Cooper4Dao;
import org.freda.cooper4.framework.dao.impl.Cooper4MongoDao;
import org.freda.cooper4.framework.dao.impl.Cooper4Reader;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

/**
 *
 * 系统的DAO与Reader
 *
 * Created by rally on 16-4-12.
 */
@Configuration
@AutoConfigureAfter({MyBatisConfiguration.class, MongoConfiguration.class})
public class MyDaoBeanConfiguration
{
    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    @Resource(name = "mongoTemplate")
    private MongoTemplate mongoTemplate;

    /**
     *
     * COOPER4 Reader
     *
     * @return FredaReader
     */
    @Bean(name = "cooper4Reader")
    public FredaReader cooper4Reader()
    {
        Cooper4Reader cooper4Reader = new Cooper4Reader();

        cooper4Reader.setSqlSessionFactory(sqlSessionFactory);

        return cooper4Reader;
    }

    /**
     *
     * COOPER4 DAO
     *
     * @return FredaDao
     */
    @Bean(name = "cooper4Dao")
    public FredaDao cooper4Dao()
    {
        Cooper4Dao cooper4Dao = new Cooper4Dao();

        cooper4Dao.setSqlSessionFactory(sqlSessionFactory);

        return cooper4Dao;
    }

    /**
     *
     * COOPER4 MONGO DAO
     *
     * @return FredaMongoDao
     */
    @Bean(name = "cooper4MongoDao")
    public FredaMongoDao cooper4MongoDao()
    {
        Cooper4MongoDao cooper4MongoDao = new Cooper4MongoDao();

        cooper4MongoDao.setMongoTemplate(mongoTemplate);

        return cooper4MongoDao;
    }


}
