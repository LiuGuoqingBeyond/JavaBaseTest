package com.sinopaylib.mapbean;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Java Bean与Map互转工具类
 */
public class TransMapToBeanUtils {

	/**
	 * javaBean 转 Map
	 * @param object 需要转换的javabean
	 * @return  转换结果map
	 * @throws Exception
	 */
	public static Map<String, Object> beanToMap(Object object) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();

		Class cls = object.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			map.put(field.getName(), field.get(object));
		}
		return map;
	}

	/**
	 *
	 * @param map   需要转换的map
	 * @param cls   目标javaBean的类对象
	 * @return  目标类object
	 * @throws Exception
	 */
	public static Object mapToBean(Map<String, String> map, Class cls) {
		Object object = null;
		try {
			object = cls.newInstance();
			for (String key : map.keySet()){
				Field temFiels = cls.getDeclaredField(key);
				temFiels.setAccessible(true);
				temFiels.set(object, map.get(key));
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return object;
	}
}
