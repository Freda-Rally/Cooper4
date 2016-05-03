package org.freda.cooper4.framework.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.datastructure.impl.BaseDto;
import org.freda.cooper4.framework.utils.FredaUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * json处理器
 * 
 * @author Administrator
 *
 */
public class JsonHelper
{
	private static Log	log	= LogFactory.getLog(JsonHelper.class);
	
	private static SerializeConfig mapping = new SerializeConfig(); 
	/*默认日期-时间格式*/
	static
	{
		mapping.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
		mapping.put(java.sql.Timestamp.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
		mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
	}
	
	/**
	 * 将Java对象系列化为Json资料格式
	 * 
	 * @param pObject
	 *            传入的Java对象
	 * @return
	 */
	public static final String encodeObject2Json(Object pObject)
	{
		String jsonString = "[]";
		if (FredaUtils.isEmpty(pObject))
		{
			// log.warn("传入的Java对象为空,不能将其序列化为Json资料格式.请检查!");
		} else {
			jsonString = JSON.toJSONString(pObject,true);			
		}
		log.debug("序列化后的JSON资料输出:\n" + jsonString);

		return jsonString;
	}

	/**
	 * 将含有日期时间格式的Java对象系列化为Json资料格式<br>
	 * Json-Lib在处理日期时间格式是需要实现其JsonValueProcessor接口,所以在这里提供一个重载的方法对含有<br>
	 * 日期时间格式的Java对象进行序列化
	 * 
	 * @param pObject
	 *            传入的Java对象
	 * @return
	 */
	public static final String encodeObject2Json(Object pObject, String pFormatString)
	{
		String jsonString = "[]";
		if (FredaUtils.isEmpty(pObject))
		{
			// log.warn("传入的Java对象为空,不能将其序列化为Json资料格式.请检查!");
		}
		else
		{
			mapping.put(java.util.Date.class, new SimpleDateFormatSerializer(pFormatString));
			mapping.put(java.sql.Timestamp.class, new SimpleDateFormatSerializer(pFormatString));
			mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(pFormatString));
			jsonString=JSON.toJSONString(pObject, mapping);
		}
		log.debug("序列化后的JSON资料输出:\n" + jsonString);
		return jsonString;
	}

	/**
	 * 将分页信息压入JSON字符串 此类内部使用,不对外暴露
	 * 
	 * @param  jsonString JSON字符串
	 * @param totalCount
	 * @return 返回合并后的字符串
	 */
	@SuppressWarnings("unused")
	private static String encodeJson2PageJson(String jsonString, Integer totalCount)
	{
		jsonString = "{\"TOTALCOUNT\":" + totalCount + ", \"ROOT\":" + jsonString + "}";

		log.debug("合并后的JSON资料输出:\n" + jsonString);

		return jsonString;
	}

	/**
	 * 直接将List转为分页所需要的Json资料格式
	 * 
	 * @param list
	 *            需要编码的List对象
	 * @param totalCount
	 *            记录总数
	 */
	@SuppressWarnings("rawtypes")
	public static final String encodeList2PageJson(List list, Integer totalCount)
	{
		String subJsonString = "";

		subJsonString = encodeObject2Json(list);

		String jsonString = "{\"TOTALCOUNT\":" + totalCount + ", \"ROOT\":" + subJsonString + "}";

		log.debug("生成的JSON资料输出:\n" + jsonString);
		return jsonString;
	}

	/**
	 * 直接将List转为分页所需要的Json资料格式
	 * 
	 * @param list
	 *            需要编码的List对象
	 * @param totalCount
	 *            记录总数
	 * @param dataFormat
	 *            时间日期格式化,传null则表明List不包含日期时间属性
	 */
	@SuppressWarnings("rawtypes")
	public static final String encodeList2PageJson(List list, Integer totalCount, String dataFormat)
	{
		String subJsonString = "";
		if (FredaUtils.isEmpty(dataFormat))
		{
			subJsonString = encodeObject2Json(list);
		}
		else
		{
			subJsonString = encodeObject2Json(list, dataFormat);
		}
		String jsonString = "{\"TOTALCOUNT\":" + totalCount + ", \"ROOT\":" + subJsonString + "}";
		return jsonString;
	}

	/**
	 * 将数据系列化为表单数据填充所需的Json格式
	 * 
	 * @param pDto
	 *            待系列化的对象
	 * @param pFormatString
	 *            日期时间格式化,如果为null则认为没有日期时间型字段
	 * @return
	 */
	public static String encodeDto2FormLoadJson(Dto pDto, String pFormatString)
	{
		String jsonString = "";
		String sunJsonString = "";
		if (FredaUtils.isEmpty(pFormatString))
		{
			sunJsonString = encodeObject2Json(pDto);
		}
		else
		{
			sunJsonString = encodeObject2Json(pDto, pFormatString);
		}
		jsonString = "{\"success\":" + (FredaUtils.isEmpty(pDto.getAsString("success")) ? "true" : pDto.getAsString("success")) + ",\"data\":" + sunJsonString + "}";
		log.debug("序列化后的JSON资料输出:\n" + jsonString);
		return jsonString;
	}

	/**
	 * 将单一Json对象解析为DTOJava对象
	 * 
	 * @param jsonString
	 *            简单的Json对象
	 * @return dto
	 */
	public static Dto parseSingleJson2Dto(String jsonString)
	{
		Dto dto = new BaseDto();
		if (FredaUtils.isEmpty(jsonString))
		{
			return dto;
		}
		dto = JSON.parseObject(jsonString,BaseDto.class);
		return dto;
	}

	/**
	 * 将复杂Json资料格式转换为List对象
	 * 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List parseJson2List(String jsonString)
	{
		List<Dto> list = new ArrayList();
		list=JSON.parseArray(jsonString, Dto.class);
		return list;
	}
}