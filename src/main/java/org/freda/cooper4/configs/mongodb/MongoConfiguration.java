package org.freda.cooper4.configs.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

/**
 *
 * Mongo Factory config
 *
 * Created by rally on 16/4/22.
 */
//@Configuration
public class MongoConfiguration implements EnvironmentAware
{

    @Autowired(required = false)
    private MongoClientOptions options;

    private RelaxedPropertyResolver propertyResolver;

    private Environment environment;

    @Override
    public void setEnvironment(Environment environment)
    {
        this.environment = environment;

        this.propertyResolver = new RelaxedPropertyResolver(environment,"mongo.");
    }

    /**
     *
     * Mongo Properties configs
     *
     * @return
     */
    @Bean
    public  MongoProperties mongoProperties()
    {
        MongoProperties mongoProperties = new MongoProperties();

        mongoProperties.setHost(propertyResolver.getProperty("host"));

        mongoProperties.setPort(Integer.parseInt(propertyResolver.getProperty("port")));

        mongoProperties.setUsername(propertyResolver.getProperty("username"));

        mongoProperties.setPassword(propertyResolver.getProperty("password").toCharArray());

        return mongoProperties;


    }

    /**
     *
     *Mongo Client Configs
     *
     * @return MongoClient
     * @throws UnknownHostException
     */
    @Bean
    public MongoClient mongo() throws UnknownHostException
    {
        return mongoProperties().createMongoClient(this.options,this.environment);
    }

    /**
     *
     *Mongo DB Factory Configs
     *
     * @param mongo
     * @return MongoDbFactory
     */
    @Bean
    public MongoDbFactory mongoDbFactory(MongoClient mongo)
    {
        SimpleMongoDbFactory simpleMongoDbFactory = new SimpleMongoDbFactory(mongo,propertyResolver.getProperty("database"));

        return  simpleMongoDbFactory;
    }

    /**
     *
     * Mongo Template Configs
     *
     * @param mongoDbFactory
     * @return MongoTemplate
     */
    @Bean(name = "mongoTemplate")
    public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory)
    {
        return new MongoTemplate(mongoDbFactory);
    }
}
