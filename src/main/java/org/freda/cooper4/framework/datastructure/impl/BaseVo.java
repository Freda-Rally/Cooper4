package org.freda.cooper4.framework.datastructure.impl;

import org.freda.cooper4.framework.datastructure.Dto;
import org.freda.cooper4.framework.utils.FredaUtils;

import java.io.Serializable;

/**
 *
 * VO的父类
 *
 * Created by rally on 16/4/21.
 */
public abstract class BaseVo implements Serializable
{
    /**
     *
     * 将值对象转换为Dto对象
     *
     * @return dto 返回的Dto对象
     */
    public Dto toDto()
    {
        Dto dto = new BaseDto();

        FredaUtils.copyPropFromBean2Dto(this, dto);

        return dto;
    }

    /**
     *
     * 将值对象转换为JSON字符串
     *
     * @return String 返回的JSON格式字符串
     */
    public String toJson()
    {
        Dto dto = toDto();

        return dto.toJson();
    }
}
