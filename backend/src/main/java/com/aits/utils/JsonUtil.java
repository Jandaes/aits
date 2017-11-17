package com.aits.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author jared
 */
public class JsonUtil {

	private final static Log logger = LogFactory.getLog(JsonUtil.class);

	private static Gson gson = null;

	static {
		gson = new Gson();
	}

	public static synchronized Gson newInstance() {
		if (gson == null) {
			gson = new Gson();
		}
		return gson;
	}

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T toBean(String json, Class<T> clz) {

		return gson.fromJson(json, clz);
	}

	public static <T> Map<String, T> toMap(String json, Class<T> clz) {
		Map<String, JsonObject> map = gson.fromJson(json, new TypeToken<Map<String, JsonObject>>() {
		}.getType());
		Map<String, T> result = new HashMap<>();
		for (String key : map.keySet()) {
			result.put(key, gson.fromJson(map.get(key), clz));
		}
		return result;
	}
	
	
    public static <K, V> Map<K, V> listToMap(List<V> list, String fieldName4Key, Class<V> c) {
        Map<K, V> map = new HashMap<K, V>();
        if (list != null) {  
            try {  
                PropertyDescriptor propDesc = new PropertyDescriptor(fieldName4Key, c);
                Method methodGetKey = propDesc.getReadMethod();
                for (int i = 0; i < list.size(); i++) {  
                    V value = list.get(i);  
                    @SuppressWarnings("unchecked")
                    K key = (K) methodGetKey.invoke(list.get(i));  
                    map.put(key, value);  
                }  
            } catch (Exception e) {
                throw new IllegalArgumentException("field can't match the key!");
            }  
        }  
  
        return map;  
    }  

	public static Map<String, Object> toMap(String json) {
		Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
		}.getType());
		return map;
	}

	public static <T> List<T> toList(String json, Class<T> clz) {
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		List<T> list = new ArrayList<>();
		for (final JsonElement elem : array) {
			list.add(gson.fromJson(elem, clz));
		}
		return list;
	}

	public static <T> List<T> setToList(Set<?> set, Class<T> claz) {
		StringBuilder json = new StringBuilder();
		List<T> list = new ArrayList<T>();
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				if (obj instanceof byte[]) {
					byte[] b = toByteArray(obj);
					try {
						String ff = new String(b);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					String str = object2json(obj);
					list.add(toBean(str, claz));
				}
				json.append(",");
			}
			char ch = 0;
			json.setCharAt(json.length() - 1, ch);
		}
		return list;
	}

	/**
	 * Byte数组转对象
	 * 
	 * @param bytes
	 * @return
	 */
	public static Object byteArrayToObject(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream byteArrayInputStream = null;
		ObjectInputStream objectInputStream = null;
		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);
			objectInputStream = new ObjectInputStream(byteArrayInputStream);
			obj = objectInputStream.readObject();
		} catch (Exception e) {
			logger.error("byteArrayToObject failed, " + e);
		} finally {
			if (byteArrayInputStream != null) {
				try {
					byteArrayInputStream.close();
				} catch (IOException e) {
					logger.error("close byteArrayInputStream failed, " + e);
				}
			}
			if (objectInputStream != null) {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					logger.error("close objectInputStream failed, " + e);
				}
			}
		}
		return obj;
	}

	/**
	 * 对象转数组
	 * 
	 * @param obj
	 * @return
	 */
	public static byte[] toByteArray(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			oos.flush();
			bytes = bos.toByteArray();
			oos.close();
			bos.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return bytes;
	}

	public static String object2json(Object obj) {
		StringBuilder json = new StringBuilder();
		if (obj == null) {
			json.append("\"\"");
		} else if (obj instanceof String || obj instanceof Integer || obj instanceof Float || obj instanceof Boolean
				|| obj instanceof Short || obj instanceof Double || obj instanceof Long || obj instanceof BigDecimal
				|| obj instanceof BigInteger || obj instanceof Byte) {
			json.append("\"").append(string2json(obj.toString())).append("\"");
		} else if (obj instanceof Object[]) {
			json.append(array2json((Object[]) obj));
		} else if (obj instanceof List) {
			json.append(list2json((List<?>) obj));
		} else if (obj instanceof Map) {
			json.append(map2json((Map<?, ?>) obj));
		} else if (obj instanceof Set) {
			json.append(set2json((Set<?>) obj));
		} else {
			json.append(bean2json(obj));
		}
		return json.toString();
	}

	public static String bean2json(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = object2json(props[i].getName());
					String value = object2json(props[i].getReadMethod().invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String list2json(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String array2json(Object[] array) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (array != null && array.length > 0) {
			for (Object obj : array) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String map2json(Map<?, ?> map) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		if (map != null && map.size() > 0) {
			for (Object key : map.keySet()) {
				json.append(object2json(key));
				json.append(":");
				json.append(object2json(map.get(key)));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	public static String set2json(Set<?> set) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (set != null && set.size() > 0) {
			for (Object obj : set) {
				json.append(object2json(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String string2json(String s) {
		if (s == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			switch (ch) {
			case '"':
				sb.append("\\\"");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\n':
				sb.append("\\n");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '/':
				sb.append("\\/");
				break;
			default:
				if (ch >= '\u0000' && ch <= '\u001F') {
					String ss = Integer.toHexString(ch);
					sb.append("\\u");
					for (int k = 0; k < 4 - ss.length(); k++) {
						sb.append('0');
					}
					sb.append(ss.toUpperCase());
				} else {
					sb.append(ch);
				}
			}
		}
		return sb.toString();
	}
}
