package org.freda.cooper4.framework.rabbitmq.listeners;

import org.freda.cooper4.framework.rabbitmq.message.MQCommandMessage;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * 
 * MQ Listener 基类
 * 
 * @author rally
 *
 */
public abstract class MQBaseListener implements MessageListener
{

	private static MQCommandMessage command = new MQCommandMessage();
	
	/**
	 * 
	 * 需要子类Listener实现
	 * 
	 * @param baseMessage
	 */
	public abstract void excuteListener(MQCommandMessage baseMessage);
	
	/**
	 * 统一实现MQ接受者的接口.
	 */
	@Override
	public void onMessage(Message message) 
	{
		this.excuteListener(command.read(message));
	}
	
	
	
}
