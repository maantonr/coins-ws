package com.mig.coins.server.base.helper;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import flexjson.JSONSerializer;

public class JsonHelper {


	public static JSONObject toJsonObject(Long data, String tag)
	// PDTE Tto Exceptions
	//			throws SystemConfigurationException
	{
		JSONObject jsonObject = new JSONObject();

		try {
			if (null != data){
				JSONObject innerObject = new JSONObject();
				innerObject.put(tag, data.toString());
				jsonObject.put("data", innerObject);
			}
		} catch (JSONException  e) {
			// PDTE Tto Exceptions
			//				throw new SystemConfigurationException(e, e.getMessage());
		}
		return jsonObject;
	}

	public static <T> JSONObject beanToJsonObject(T bean)
	// PDTE Tto Exceptions
	//			throws SystemConfigurationException
	{
		JSONObject jsonObject = new JSONObject();

		try {
			if (null != bean){
				JSONObject innerObject = new JSONObject(new JSONSerializer().exclude("*.class").deepSerialize(bean));
				jsonObject.put("data", innerObject);
			}
		} catch (JSONException  e) {
			// PDTE Tto Exceptions
			//				throw new SystemConfigurationException(e, e.getMessage());
		}
		return jsonObject;
	}

	public static <T> JSONObject collectionToJsonObject(Collection<T> collResultBean) 
	// PDTE Tto Exceptions
	//			throws SystemConfigurationException
	{
		return collectionToJsonObject(collResultBean, null);
	}

	private static <T> JSONObject collectionToJsonObject(Collection<T> collResultBean, List<String> excludeFields) 
	// PDTE Tto Exceptions
	//			throws SystemConfigurationException
	{
		JSONObject jsonObject = new JSONObject();

		try {
			if (null == collResultBean) {
				jsonObject.put("data", "[]");
			} else {
				JSONArray jsonArray = collectionToJsonArray(collResultBean, excludeFields);
				jsonObject.put("data", jsonArray);
			}

		} catch (JSONException  e) {
			// PDTE Tto Exceptions
			//				throw new SystemConfigurationException(e, e.getMessage());
		}
		return jsonObject;
	}

	private static <T> JSONArray collectionToJsonArray(Collection<T> collResultBean, List<String> excludeFields) 
	// PDTE Tto Exceptions
	//			throws SystemConfigurationException
	{
		JSONArray jsonArray = null;

		try {
			JSONSerializer serializer = new JSONSerializer();
			if ((null != excludeFields) && (!excludeFields.isEmpty())) {
				serializer.setExcludes(excludeFields);
			}
			jsonArray = new JSONArray(serializer.exclude("*.class").deepSerialize(collResultBean));
		} catch (JSONException  e) {
			// PDTE Tto Exceptions
			//				throw new SystemConfigurationException(e, e.getMessage());
		}
		return jsonArray;
	}

	public static <T, V> JSONObject collectionToJsonObject(T bean, Collection<V> collBean)
	// PDTE Tto Exceptions
	//			throws SystemConfigurationException
	{
		Collection<T> collResultBean = copyCollection(bean, collBean);
		JSONObject jsonObject = collectionToJsonObject(collResultBean, null);

		return jsonObject;
	}

	private static <T, V> Collection<T> copyCollection(T resultBean, Collection<V> collQueryBean) 
	// PDTE Tto Exceptions
	//			throws SystemConfigurationException
	{
		Collection<T> newCollect = null;
		try {

			if ((null != collQueryBean) && (!collQueryBean.isEmpty())) {
				newCollect = new ArrayList<T>();
				for (final Iterator<V> iterator = collQueryBean.iterator(); iterator.hasNext();) {
					T newBean = (T) resultBean.getClass().newInstance();
					copyObject(newBean, iterator.next());				
					newCollect.add(newBean);
				}
			}

		} catch (InstantiationException | IllegalAccessException e) {
			// PDTE Tto Exceptions
			//				throw new SystemConfigurationException(e, e.getMessage());
		}

		return newCollect;
	}


	private static <T, V> T copyObject(T t, V data) 
	// PDTE Tto Exceptions
	//			throws SystemConfigurationException
	{
		try {
			BeanUtils.copyProperties(t, data);
		} catch (IllegalAccessException | InvocationTargetException e) {
			//				// PDTE Tto Exceptions
			//				//				throw new SystemConfigurationException(e, e.getMessage());
			e.printStackTrace();
		}

		return t;
	}

}
