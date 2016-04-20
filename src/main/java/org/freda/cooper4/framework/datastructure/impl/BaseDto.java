package org.freda.cooper4.framework.datastructure.impl;

import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.json.JsonHelper;
import org.freda.cooper4.framework.utils.TypeCaseHelper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * BaseDto
 * 
 * @author Rally
 *
 */
@SuppressWarnings({ "rawtypes", "serial" })
public class BaseDto extends HashMap implements Dto, Serializable
{

	public BaseDto()
	{
	}

	@SuppressWarnings("unchecked")
	public BaseDto(String key, Object value)
	{
		put(key, value);
	}

	public BaseDto(Boolean success)
	{
		setSuccess(success);
	}

	public BaseDto(Boolean success, String msg)
	{
		setSuccess(success);
		setMsg(msg);
	}
	
	public BaseDto(Boolean success , Object obj)
	{
		setSuccess(success);
		setResultData(obj);
	}

	/**
	 * 以BigDecimal类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return BigDecimal 键值
	 */
	@Override
	public BigDecimal getAsBigDecimal(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "BigDecimal", null);
		if (obj != null)
			return (BigDecimal) obj;
		else
			return null;
	}

	/**
	 * 以Date类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Date 键值
	 */
	@Override
	public Date getAsDate(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd");
		if (obj != null)
			return (Date) obj;
		else
			return null;
	}

	/**
	 * 以Integer类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Integer 键值
	 */
	@Override
	public Integer getAsInteger(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Integer", null);
		if (obj != null)
			return (Integer) obj;
		else
			return null;
	}

	/**
	 * 以Long类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Long 键值
	 */
	@Override
	public Long getAsLong(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Long", null);
		if (obj != null)
			return (Long) obj;
		else
			return null;
	}

	/**
	 * 以String类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return String 键值
	 */
	@Override
	public String getAsString(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "String", null);
		if (obj != null)
			return (String) obj;
		else
			return "";
	}

	/**
	 * 以List类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return List 键值
	 */
	@Override
	public List getAsList(String key)
	{
		return (List) get(key);
	}

	/**
	 * 以Timestamp类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Timestamp 键值
	 */
	@Override
	public Timestamp getAsTimestamp(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Timestamp", "yyyy-MM-dd HH:mm:ss");
		if (obj != null)
			return (Timestamp) obj;
		else
			return null;
	}

	/**
	 * 以Boolean类型返回键值
	 * 
	 * @param key
	 *            键名
	 * @return Timestamp 键值
	 */
	@Override
	public Boolean getAsBoolean(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Boolean", null);
		if (obj != null)
			return (Boolean) obj;
		else
			return null;
	}

	/**
	 * 给Dto压入第一个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param pList
	 *            压入Dto的List对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setDefaultAList(List pList)
	{
		put("defaultAList", pList);
	}

	/**
	 * 给Dto压入第二个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 * 
	 * @param pList
	 *            压入Dto的List对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setDefaultBList(List pList)
	{
		put("defaultBList", pList);
	}

	/**
	 * 获取第一个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 *
	 *            压入Dto的List对象
	 */
	@Override
	public List getDefaultAList()
	{
		return (List) get("defaultAList");
	}

	/**
	 * 获取第二个默认List对象<br>
	 * 为了方便存取(省去根据Key来存取和类型转换的过程)
	 *
	 *            压入Dto的List对象
	 */
	@Override
	public List getDefaultBList()
	{
		return (List) get("defaultBList");
	}

	/**
	 * 给Dto压入一个默认的Json格式字符串
	 * 
	 * @param jsonString
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setDefaultJson(String jsonString)
	{
		put("defaultJsonString", jsonString);
	}

	/**
	 * 获取默认的Json格式字符串
	 * 
	 * @return
	 */
	@Override
	public String getDefaultJson()
	{
		return getAsString("defaultJsonString");
	}

	/**
	 * 将此Dto对象转换为Json格式字符串<br>
	 * 
	 * @return string 返回Json格式字符串
	 */
	@Override
	public String toJson()
	{
		String strJson = null;
		strJson = JsonHelper.encodeObject2Json(this);
		return strJson;
	}

	/**
	 * 设置交易状态
	 * 
	 * @param pSuccess
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setSuccess(Boolean pSuccess)
	{
		put("success", pSuccess);
		if (pSuccess) {
			put("bflag", "1");
		} else {
			put("bflag", "0");
		}

	}

	/**
	 * 获取交易状态
	 *
	 */
	@Override
	public Boolean getSuccess()
	{
		return getAsBoolean("success");
	}

	/**
	 * 设置交易提示信息
	 * 
	 * @param pMsg
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void setMsg(String pMsg)
	{
		put("msg", pMsg);
	}

	/**
	 * 获取交易提示信息
	 *
	 */
	@Override
	public String getMsg() 
	{
		return getAsString("msg");
	}
	/**
	 * 设置返回结果
	  * 
	  * @author wkgi-Rally
	  * @date 2015年10月8日 上午9:47:26
	  * @param obj
	 */
	@SuppressWarnings("unchecked")
	public void setResultData(Object obj)
	{
		put("data", obj);
	}
	/**
	 * 获取返回结果
	  * 
	  * @author wkgi-Rally
	  * @date 2015年10月8日 上午9:47:38
	  * @return obj
	 */
	public Object getResultData()
	{
		return this.get("data");
	}
	
	
}
