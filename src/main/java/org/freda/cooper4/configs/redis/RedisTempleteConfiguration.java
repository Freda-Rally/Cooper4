package org.freda.cooper4.configs.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 *
 *
 *
 * Created by rally on 16-3-24.
 */
//@Configuration
//@AutoConfigureAfter({RedisConnectionFactoryConfiguration.class})
public class RedisTempleteConfiguration implements EnvironmentAware
{
    private RelaxedPropertyResolver propertyResolver;

    private static final Log log = LogFactory.getLog(RedisTempleteConfiguration.class);

    @Resource(name = "jedisConnectionFactory")
    private RedisConnectionFactory redisConnectionFactory;

    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"redis.");
    }

    /**
     *
     * RedisTemplate
     *
     * @return RedisTemplate
     */
    @Primary
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate()
    {
        log.debug("Redis Template Configure!");

        RedisTemplate redisTemplate = new RedisTemplate();

        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());

        redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());

        return redisTemplate;
    }

}
