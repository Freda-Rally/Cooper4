package org.freda.cooper4.framework.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.datastructure.impl.BaseDto;

import java.lang.reflect.Field;
import java.util.Iterator;

/**
 *
 * 反射机制工具集
 * 主要提供Bean to Dto.Dto to Bean.
 * 实例化Class.等等.
 *
 * Created by rally on 16/4/24.
 */
public class BeanUtils
{
    private static final Log log = LogFactory.getLog(BeanUtils.class);

    /**
     *
     * 将给定的Bean中的成员变量值.以键值对的方式放入Dto中.
     *
     * @param bean
     * @param <T>
     * @return Dto
     * @throws IllegalAccessException
     */
    public static <T> Dto Bean2Dto(T bean) throws IllegalAccessException
    {
        Field[] fields = bean.getClass().getDeclaredFields();

        log.debug("将Bean中数据.压入Dto中..");

        if (fields != null)
        {
            Dto resultDto = new BaseDto();

            for (Field field : fields)
            {
                field.setAccessible(true);

                log.debug("Key: " + field.getName() + "Value: " + field.get(bean));

                resultDto.put(field.getName(),field.get(bean));
            }
            return resultDto;
        }
        return null;
    }

    /**
     *
     * 将Dto中的数据.封装到Bean中..
     *
     * @param tClass
     * @param pDto
     * @param <T>
     * @return T
     */
    public static <T> T Dto2Bean(Class<T> tClass ,final Dto pDto) throws IllegalAccessException, InstantiationException
    {
        if (FredaUtils.isNotEmpty(pDto))
        {
            T bean = tClass.newInstance();

            Iterator keyIterator = pDto.keySet().iterator();

            Field[] fields = tClass.getDeclaredFields();

            log.debug("将Dto中数据.压入Bean中..");

            for (Field field : fields)
            {
                field.setAccessible(true);

                while (keyIterator.hasNext())
                {
                    Object key = keyIterator.next();

                    if (key.equals(field.getName()))
                    {
                        log.debug("Key: " + field.getName() + "Value: " + pDto.get(key));

                        field.set(bean,pDto.get(key));
                    }
                }
            }
            return bean;
        }
        return null;
    }
}
