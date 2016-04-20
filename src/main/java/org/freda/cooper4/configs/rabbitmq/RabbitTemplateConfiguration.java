package org.freda.cooper4.configs.rabbitmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 *
 * Rabbit_MQ Template Config
 *
 * Created by rally on 16-3-25.
 */
//@Configuration
public class RabbitTemplateConfiguration implements EnvironmentAware
{
    private RelaxedPropertyResolver propertyResolver;

    private static final Log log = LogFactory.getLog(RabbitTemplateConfiguration.class);

    @Resource(name = "connectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"rabbitmq.");
    }

    /**
     *
     * Exchange
     *
     * @return DirectExchange
     */
    @Bean
    public DirectExchange exchange()
    {
        log.info("RabbitMQ Exchange Configure!");

        return new DirectExchange(propertyResolver.getProperty("exchange"));
    }

    /**
     *
     * QUEUE
     *
     * @return Queue
     */
    @Bean
    public Queue queue()
    {
        log.info("RabbitMQ Queue Configure!");

        return new Queue(propertyResolver.getProperty("queue"));
    }

    /**
     *
     * Binding
     *
     * @return Binding
     */
    @Bean
    public Binding exchangeBinding()
    {
        log.info("RabbitMQ Binding Configure!");

        return BindingBuilder.bind(queue()).to(exchange()).with(propertyResolver.getProperty("route"));
    }

    /**
     *
     * RabbitTemplate
     *
     * @return RabbitTemplate
     */
    @Bean(name = "rabbitTemplate")
    public RabbitTemplate rabbitTemplate()
    {
        log.info("Rabbit Template Configure!");

        RabbitTemplate rabbitTemplate = new RabbitTemplate();

        rabbitTemplate.setConnectionFactory(connectionFactory);

        rabbitTemplate.setExchange(propertyResolver.getProperty("exchange"));

        rabbitTemplate.setRoutingKey(propertyResolver.getProperty("route"));

        return rabbitTemplate;
    }
}

