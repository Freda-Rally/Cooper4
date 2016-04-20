package org.freda.cooper4.configs.redis;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;

/**
 *
 * Jedis Connection Factory Config
 *
 * Created by rally on 16-3-24.
 */
//@Configuration
//@AutoConfigureAfter({RedisPoolConfiguration.class})
public class RedisConnectionFactoryConfiguration implements EnvironmentAware
{
    private RelaxedPropertyResolver propertyResolver;

    private static final Log log = LogFactory.getLog(RedisConnectionFactoryConfiguration.class);

    @Resource(name = "jedisPoolConfig")
    private JedisPoolConfig jedisPoolConfig;

    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"redis.");
    }

    /**
     *
     * JedisConnectionFactory
     *
     * @return jedisConnectionFactory
     */
    @Bean(name = "jedisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory()
    {
        log.debug("Jedis Connection Factory Configure!");

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory(jedisPoolConfig);

        jedisConnectionFactory.setHostName(propertyResolver.getProperty("rl.host"));

        jedisConnectionFactory.setPort(Integer.parseInt(propertyResolver.getProperty("rl.port")));

        jedisConnectionFactory.setTimeout(Integer.parseInt(propertyResolver.getProperty("rl.timeout")));

        jedisConnectionFactory.setPassword(propertyResolver.getProperty("rl.password"));

        return jedisConnectionFactory;
    }
}
