package org.freda.cooper4.framework.rabbitmq.message;

import com.alibaba.fastjson.JSON;

/**
 * 
 * 使用Model的Message
 * 
 * @author rally
 *
 */
public class MQModelMessage extends MQCommandMessage
{
	
	//使用系统中的model作为Message的Body
	private String modelBeanJsonStr = null;
	/**
	 * 推送时
	 */
	public MQModelMessage(byte[] commandId,Object model)
	{
		this.header = new MQMessageHeader(commandId);
		this.header.setIsUseModel((byte)1);
		this.setModelBean(model);
	}
	/**
	 * 读取方式
	 * @param command
	 * @param c
	 */
	public MQModelMessage(MQCommandMessage command)
	{
		this.header = command.header;
		this.bodyBytes = command.bodyBytes;
		
		this.modelBeanJsonStr = new String(bodyBytes);
	}
	/**
	 * 
	 * 如果使用系统自己的Model作为Message的Body传递..请调用SetModel
	 * 
	 * @param model Object
	 */
	public void setModelBean(Object model)
	{
		this.modelBeanJsonStr = JSON.toJSONString(model);
	}
	/**
	 * 命令处理完成..获取Model
	 * @return
	 */
	public Object getModelData(Class<?> c)
	{
		return JSON.parseObject(this.modelBeanJsonStr, c);
	}
	/**
	 * 重写命令类中的方法
	 */
	protected void createBody()
	{
		if(this.header.getIsUseModel() == 1)
		{
			this.bodyBytes = this.modelBeanJsonStr.getBytes();
		}
	}
}
