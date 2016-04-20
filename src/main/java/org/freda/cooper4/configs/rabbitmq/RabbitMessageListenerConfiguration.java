package org.freda.cooper4.configs.rabbitmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;

/**
 *
 * RabbitMQ Listener Config
 *
 * Created by rally on 16-3-25.
 */
//@Configuration
public class RabbitMessageListenerConfiguration implements EnvironmentAware
{
    private RelaxedPropertyResolver propertyResolver;

    private static final Log log = LogFactory.getLog(RabbitMessageListenerConfiguration.class);

    @Resource(name = "connectionFactory")
    private ConnectionFactory connectionFactory;

    @Override
    public void setEnvironment(Environment environment)
    {
        this.propertyResolver = new RelaxedPropertyResolver(environment,"rabbitmq.");
    }


    /**
     *
     * 里面填入自己的Listener
     *
     * 例如:
     *
     *  @Bean
        public ExampleListener plainExampleListener()
        {
            ExampleListener exampleListener = new ExampleListener();
            return exampleListener;
        }

         @Bean
         public MessageListener exampleListener()
         {
             MessageListener listener = new MessageListenerAdapter(plainExampleListener());
             return listener;
         }
     *
     */

    /**
     *
     * Simple Message Listener Container
     *
     * @return SimpleMessageListenerContainer
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer()
    {
        log.debug("Rabbit Simple Message Listener Container Configure!");

        SimpleMessageListenerContainer messageListenerContainer = new SimpleMessageListenerContainer();

        messageListenerContainer.setConnectionFactory(connectionFactory);

        messageListenerContainer.setConcurrentConsumers(Integer.parseInt(propertyResolver.getRequiredProperty("lc.concurrency")));

        messageListenerContainer.setMaxConcurrentConsumers(Integer.parseInt(propertyResolver.getRequiredProperty("lc.maxConcurrency")));

        messageListenerContainer.setPrefetchCount(Integer.parseInt(propertyResolver.getRequiredProperty("lc.prefetch")));

        return messageListenerContainer;
    }
}
