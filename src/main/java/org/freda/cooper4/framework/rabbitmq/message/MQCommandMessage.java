package org.freda.cooper4.framework.rabbitmq.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.framework.rabbitmq.utils.BytesAndIntUtil;
import org.freda.cooper4.framework.rabbitmq.utils.MQCommandDefine;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 消息任务类
 * 
 * @author rally
 *
 */
public class MQCommandMessage 
{
	private static final Log log = LogFactory.getLog(MQCommandMessage.class);
	//消息头
	public MQMessageHeader header = new MQMessageHeader();
	//消息体的Byte数组
	protected byte[] bodyBytes = new byte[0];
	/**
	 * 
	 *	获取整体命令消息的bytes
	 *
	 * @return byte[]
	 */
	protected byte[] getAllCommandbytes()
	{
		this.createBody();
		
		byte[] allCommandbytes = new byte[MQCommandDefine.MQ_HEADER_LEN + this.bodyBytes.length];
		//拷贝消息头
		System.arraycopy(this.header.getCommandHeaderBytes(), 0, allCommandbytes, 0, MQCommandDefine.MQ_HEADER_LEN);
		//拷贝消息体
		System.arraycopy(this.bodyBytes, 0, allCommandbytes, MQCommandDefine.MQ_HEADER_LEN, this.bodyBytes.length);
		
		log.debug("message all bytes created");
		
		return allCommandbytes;
	}
	/**
	 * 
	 * 将数据入读bodybytes
	 * 
	 * @param message
	 */
	protected void readDataIntoBody(Message message) 
	{
		byte[] CommandTemp = (byte[])new SimpleMessageConverter().fromMessage(message);
		
		this.bodyBytes = new byte[CommandTemp.length - MQCommandDefine.MQ_HEADER_LEN];
		
		System.arraycopy(CommandTemp, MQCommandDefine.MQ_HEADER_LEN, this.bodyBytes, 0, this.bodyBytes.length);
		
		log.debug("read body success!");
	}
	/**
	 * 
	 * 读取消息
	 * 
	 * @param message
	 * @return
	 */
	public MQCommandMessage read(Message message)
	{
		this.header.readHeader(message);
		
		this.readDataIntoBody(message);
		
		log.debug("read message success!");
		
		switch (BytesAndIntUtil.Bytes4ToInt(this.header.getCommandId()))
		{
			case 0x00000001:
				//TODO
		}
		return null;
	}
	/**
	 * 向MQ服务器推送消息
	 * @param template
	 */
	public void push(String exchange, AmqpTemplate template)
	{
		byte[] commandBytes = this.getAllCommandbytes();
		
		template.convertAndSend(exchange, null, commandBytes);
		
		log.debug("message is send!");
	}
	/**
	 * 如果使用自己定义的Message..将Bean转成JSON并写入bodyBytes..
	 */
	protected void createBody()
	{
		log.debug("use Command Bean!");
		
		this.bodyBytes = JSON.toJSONString(this).getBytes();			
	}
}
