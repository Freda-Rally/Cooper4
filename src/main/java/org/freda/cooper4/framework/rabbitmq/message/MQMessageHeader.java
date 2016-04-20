package org.freda.cooper4.framework.rabbitmq.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.framework.rabbitmq.utils.MQCommandDefine;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.SimpleMessageConverter;

/**
 * 消息头
 * @author rally
 *
 */
public class MQMessageHeader 
{
	private static final Log log = LogFactory.getLog(MQMessageHeader.class);
	
	/**
	 * 消息命令号
	 */
	private byte[] commandId;
	/**
	 * 时间戳
	 */
	private String timeStamp;
	/**
	 * 是否使用系统内部Model
	 */
	private byte isUseModel = 0;
	/**
	 * 最新消费时间
	 */
	private String doStamp;
	/**
	 * 已消费次数
	 */
	private byte doTimes = -1;
	/**
	 * 默认构造方法
	 */
	public MQMessageHeader(){}
	/**
	 * 创建时
	 * @param command byte[]
	 */
	public MQMessageHeader(byte[] command)
	{
		this.commandId = command;
	}

	public byte[] getCommandId() 
	{
		return commandId;
	}

	public void setCommandId(byte[] commandId) 
	{
		this.commandId = commandId;
	}

	public String getTimeStamp() 
	{
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) 
	{
		this.timeStamp = timeStamp;
	}
	
	public byte getIsUseModel() 
	{
		return isUseModel;
	}
	
	public void setIsUseModel(byte isUseModel) 
	{
		this.isUseModel = isUseModel;
	}

	public byte getDoTimes()
	{
		return doTimes;
	}

	public void setDoTimes(byte doTimes)
	{
		this.doTimes = doTimes;
	}

	public String getDoStamp()
	{
		return doStamp;
	}

	public void setDoStamp(String doStamp)
	{
		this.doStamp = doStamp;
	}
	/**
	 * 读取消息头信息
	 * @param message
	 * @throws Exception
	 */
	protected void readHeader(Message message)
	{	
		//将Message以byte[]形式传入
		byte[] headBytes = new byte[MQCommandDefine.MQ_HEADER_LEN];
		System.arraycopy((byte[])new SimpleMessageConverter().fromMessage(message), 0, headBytes, 0, MQCommandDefine.MQ_HEADER_LEN);
		
		//CommandId
		byte[] temp = new byte[4];
		System.arraycopy(headBytes, 0, temp, 0, 4);
		this.commandId = temp;
		//timeStamp
		temp = new byte[13];
		System.arraycopy(headBytes, 4, temp, 0, 13);
		this.timeStamp = new String(temp).trim();
		//isUseModel
		this.isUseModel = headBytes[17];
		//doStamp
		temp = new byte[13];
		System.arraycopy(headBytes,17,temp,0,13);
		this.doStamp = new String(temp).trim();
		//doTimes
		this.doTimes = headBytes[31];
		log.debug("read message header success!");
		
	}
	/**
	 * 
	 * 消息头生成bytes
	 * 
	 * @return byte[]
	 */
	protected byte[] getCommandHeaderBytes()
	{	
		byte[] commandHeaderBytes = new byte[MQCommandDefine.MQ_HEADER_LEN];
		if (this.timeStamp == null && this.timeStamp.equals(""))
		{
			this.timeStamp = System.currentTimeMillis() + "";
		}
		if (this.doTimes > -1)
		{
			this.doTimes++;
		}
		this.doStamp = System.currentTimeMillis() + "";
		//CommandId
		System.arraycopy(commandId, 0, commandHeaderBytes, 0, 4);
		//TimeStamp
		System.arraycopy(this.timeStamp.getBytes(), 0, commandHeaderBytes, 4, 13);
		//isUseModel
		commandHeaderBytes[17] = isUseModel;
		//doStamp
		System.arraycopy(this.doStamp.getBytes(),0,commandHeaderBytes,18,13);
		//doTimes
		commandHeaderBytes[31] = doTimes;
		log.debug("create header bytes success");
		
		return commandHeaderBytes;
	}
}
