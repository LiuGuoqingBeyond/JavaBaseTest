package com.uppayplugin.unionpay.javabasetest.utils;


import com.orhanobut.logger.Logger;
import com.uppayplugin.unionpay.libcommon.aes.AESEncryptor;
import com.uppayplugin.unionpay.libcommon.des.Des;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @description JSON解析(推荐使用jsonToMap2解析JOSN字符串)
 * @author King_wangyao
 * @date 2014-1-15
 * @version 1.0.0
 *
 */
public class JSONUtil {

	private static final String TAG = "JSONUtil";

	private static final String MIDDLE_KUAHUA = "[";

	private static final String DA_KUAHUA = "{";

	/**
	 * 得到JSON返回的结果
	 * @param jsonData
	 * @param type
	 * @return
	 */
	public static String getJsonResult(String jsonData, String type) {
		try {
			JSONObject jsonObject = new JSONObject(jsonData);
			return jsonObject.optString(type);
		} catch (JSONException e) {
			Logger.e(TAG, e.getMessage());
			return "-1";
		}
	}

	public static List<Map<String, Object>> getJsonData(String data) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		JSONArray jsonarray = null;
		JSONObject json = null;
		Map<String, Object> map = null;
		try {
			jsonarray = new JSONArray(data);
			for (int i = 0; i < jsonarray.length(); i++) {
				json = jsonarray.getJSONObject(i);
				map = jsonToMap(json);
				list.add(map);
			}
		} catch (JSONException e) {
			Logger.e(TAG, e.getMessage());
			try {
				json = new JSONObject(data);
				map = jsonToMap(json);
				list.add(map);
			} catch (JSONException e1) {
				Logger.e(TAG, "转换为json出现错误！");
				return list;
			}
		}

		return list;
	}

	/**
	 * 获取JSON中的数据集
	 * @param jsonObject
	 * @param dataType 需要获取的数据信息类型
	 * @return
	 */
	public static List<Map<String, Object>> getJsonData(JSONObject jsonObject, String dataType) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = null;
		JSONArray jsonarray = null;
		JSONObject json = null;
		String data = null;
		try {
			data = jsonObject.optString(dataType);
			if (data == null) {
				return list;
			}
			jsonarray = new JSONArray(data);
			for (int i = 0; i < jsonarray.length(); i++) {
				json = jsonarray.getJSONObject(i);
				map = jsonToMap(json);
				list.add(map);
			}
		} catch (JSONException e) {
			Logger.e(TAG, e.getMessage());
			map = jsonToMap(data);
			list.add(map);
		}

		return list;
	}

	/**
	 * 把JSON字符串转换为Map对象
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(String json) {
		Map<String, Object> map = new HashMap<String, Object>();
		String key = "";
		String val = "";
		try {
			JSONObject jsonObject = new JSONObject(json);
			Iterator<String> it = jsonObject.keys();
			while (it.hasNext()) {
				key = it.next();
				val = jsonObject.optString(key, "");
				map.put(key, val);
			}
		} catch (JSONException e) {
			Logger.e(TAG, e.getMessage());
		}
		return map;
	}

	/**
	 * 把JSON对象转换为Map对象
	 * @param json
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> jsonToMap(JSONObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		String key = "";
		String val = "";
		Iterator<String> it = json.keys();
		while (it.hasNext()) {
			key = it.next();
			val = json.optString(key, "");
			map.put(key, val);
		}
		return map;
	}

	/**
	 * 把JSON对象转换为Object对象（Object要么是Map是么是List）
	 * @param json
	 * @return
	 */
	public static Object jsonToMap2(String json) throws JSONException {
		if (null == json) {
			return null;
		} else if (json.startsWith(MIDDLE_KUAHUA)) {
			return jsonToMapArray(json);
		} else if (json.startsWith(DA_KUAHUA)) {
			JSONObject jsonObject = new JSONObject(json);
			return processJson(jsonObject);
		} else {
			return null;
		}
	}

	/**
	 * JSONArray
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	private static List<Map<String, Object>> jsonToMapArray(String json) throws JSONException {
		JSONArray jsonArray = new JSONArray(json);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		JSONObject jsonObject = null;
		Map<String, Object> map = null;
		for (int i = 0; i < jsonArray.length(); i++) {
			jsonObject = jsonArray.getJSONObject(i);
			map = processJson(jsonObject);
			list.add(map);
		}

		return list;
	}

	/**
	 * JSOBObject
	 * @param
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static Map<String, Object> processJson(JSONObject jsonObject) throws JSONException {
		Map<String, Object> map = new HashMap<String, Object>();

		Iterator ite = jsonObject.keys();
		String key = null;
		String value = null;
		while (ite.hasNext()) {
			key = (String) ite.next();
			value = jsonObject.optString(key);
			if (value.startsWith(MIDDLE_KUAHUA) || value.startsWith(DA_KUAHUA)) {
				map.put(key, jsonToMap2(value));
			} else {
				map.put(key, value);
			}
		}
		return map;
	}

	/**
	 * 参数json串加密 DES
	 *
	 * @param requestJson
	 * @return
	 */
	public static JSONObject formatJson(JSONObject requestJson, String password) {
		JSONObject jsonObject = new JSONObject();
		try {
			Logger.e(TAG, "requestJson=" + requestJson.toString());
		//	System.out.println(StringUtil.byteTostrGBK(requestJson.toString().getBytes("utf-8")));
			//Logger.e("requestJson",requestJson.toString());
			byte[] temp = Des.desCrypto(requestJson.toString().getBytes("utf-8"), password);
		//	Logger.e("encrty", Arrays.toString(temp));
			jsonObject.put("code", Des.byte2hex(temp));
			Logger.e("jsonObject",jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		//	Logger.e(TAG, e.getMessage());
		}
		return jsonObject;
	}

	/**
	 * AES
	 * @param requestJson
	 * @param password
	 * @return
	 */
	public static  JSONObject formatAESJson(JSONObject requestJson, String password){
		JSONObject jsonObject=new JSONObject();
		try {
			Logger.e("json",requestJson.toString());
			//Logger.e("passwrod",password);
			String json= AESEncryptor.encrypt(password,requestJson.toString());
			//Logger.e("json",json);
			jsonObject.put("code",json);
			Logger.e("jsonObject",jsonObject.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
