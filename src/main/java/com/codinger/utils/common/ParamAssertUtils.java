package com.codinger.utils.common;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;

import com.codinger.exceptions.InvalidParamException;
import com.google.gson.Gson;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;


public class ParamAssertUtils
{		
	/**
	 * 检验参数非空 支持 string、collection、普通object、map
	 * @param param
	 * @param paramName
	 */
	public static void assertParamNonEmpty(Object param, String paramName) throws InvalidParamException
	{
		String errMsg = "Invalid Parameter :" + paramName + " is empty";
		if (null == param)
		{
			throw new InvalidParamException(errMsg);
		}
		else if (String.class.isInstance(param))
		{
			if (StringUtils.isEmpty((String) param))
			{
				throw new InvalidParamException(errMsg);
			}
		}
		else if (Collection.class.isInstance(param))
		{
			if (CollectionUtils.isEmpty((Collection<?>)param))
			{
				throw new InvalidParamException(errMsg);
			}
		} 
		else if (Map.class.isInstance(param))
		{
			if (((Map<?, ?>)param).isEmpty())
			{
				throw new InvalidParamException(errMsg);
			}
		} 
	}
	
	/**
	 * 检验参数是否在合法范围之内
	 * @param param
	 * @param paramName
	 */
	public static <T> void assertParamInRange  (T param, List<T> range, String paramName)  throws InvalidParamException
	{
		if (null != range && !range.contains(param))
		{
			Gson gson = new Gson();
			String errMsg = "Invalid Parameter :" + paramName + " not in range : " + gson.toJson(range);
			throw new InvalidParamException(errMsg);
		}
	}	
}
