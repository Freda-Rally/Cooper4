package org.freda.cooper4.configs.rabbitmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/**
 *
 * Rabbit_MQ Connection Factory Config
 *
 * Created by rally on 16-3-25.
 */
//@Configuration
public class RabbitConnectionFactoryConfiguration implements EnvironmentAware
{
    private RelaxedPropertyResolver propertyResolver;

    private static final Log log = LogFactory.getLog(RabbitConnectionFactoryConfiguration.class);

    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"rabbitmq.");
    }

    /**
     *
     * Caching Connection Factory
     *
     * @return CachingConnectionFactory
     */
    @Bean(name = "connectionFactory")
    public CachingConnectionFactory connectionFactory()
    {
        log.info("RabbitMQ Caching Connection Factory Configure!");

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();

        connectionFactory.setUsername(propertyResolver.getRequiredProperty("rl.username"));

        connectionFactory.setPassword(propertyResolver.getRequiredProperty("rl.password"));

        connectionFactory.setHost(propertyResolver.getRequiredProperty("rl.host"));

        connectionFactory.setPort(Integer.parseInt(propertyResolver.getRequiredProperty("rl.port")));

        connectionFactory.setVirtualHost(propertyResolver.getRequiredProperty("rl.virtualHost"));

        return connectionFactory;
    }

}
