package mblog.core.context;

import java.util.HashMap;
import java.util.Map;

import mblog.core.utils.PropertiesLoader;

/**
 * mblog 全局配置类
 */
public class Global {
	
	/**
	 * 保存全局属性值
	 */
	private static Map<String, String> map = new HashMap<String,String>();
	
	/**
	 * 属性文件加载对象
	 */
	private static PropertiesLoader propertiesLoader = new PropertiesLoader("init.properties");
	
	/**
	 * 获取配置
	 */
	public static String getConfig(String key) {
		String value = map.get(key);
		if (value == null){
			value = propertiesLoader.getProperty(key);
			map.put(key, value);
		}
		return value;
	}

	/////////////////////////////////////////////////////////
	
	/**
	 * 获取文件存储-根目录
	 */
	public static String getRoot() {
		return getConfig("rootPath");
	}
	
	/**
	 * 文件存储-原文件目录
	 */
	public static String getOrigDir() {
		return getConfig("origDir");
	}
	
	/**
	 * 文件存储-压缩目录
	 */
	public static String getThumbsDir() {
		return getConfig("thumbsDir");
	}
	
	/**
	 * 文件存储-头像目录
	 */
	public static String getAvaDir() {
		return getConfig("avaDir");
	}

	/**
	 * 文件存储-临时文件目录
	 */
	public static String getTempDir() {
		return getConfig("tempDir");
	}
	
}
