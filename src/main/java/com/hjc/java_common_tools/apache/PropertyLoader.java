package com.hjc.java_common_tools.apache;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 从CONF_FILE_NAME加载配置文件（针对普通配置文件，非xml,json等格式的）。main方法中有基础用法。
 * 如果需要读取xml配置文件，可以通过XMLPropertiesConfiguration 进行操作。
 * 
 * maven 依赖：
 * 
 * <pre>
 * 		<dependency>
 * 			<groupId>commons-configuration</groupId>
 * 			<artifactId>commons-configuration</artifactId>
 * 			<version>${commons-configuration}</version>
 * 		</dependency>
 * </pre>
 * */
public final class PropertyLoader {
	private static PropertiesConfiguration CONF = null;

	private static String CONF_FILE_NAME = "env.properties";

	static {
		try {
			// 注意路径默认指向的是classpath的根目录
			PropertyLoader.CONF = new PropertiesConfiguration(CONF_FILE_NAME);
			PropertyLoader.CONF.setEncoding("utf-8");
			if (PropertyLoader.CONF.isEmpty()) {
				throw new IllegalArgumentException(
						"System Error: properties cannot be empty file !");
			}
		} catch (ConfigurationException e) {
			throw new RuntimeException("exception while load file:"
					+ CONF_FILE_NAME, e);
		}
	}

	public static boolean getBoolean(String key) {
		return PropertyLoader.CONF.getBoolean(key);
	}

	public static boolean getBoolean(String key, boolean defaultValue) {
		return PropertyLoader.CONF.getBoolean(key, defaultValue);
	}

	public static int getInt(String key) {
		return PropertyLoader.CONF.getInt(key);
	}

	public static int getInt(String key, int defaultValue) {
		return PropertyLoader.CONF.getInt(key, defaultValue);
	}

	public static double getDouble(String key) {
		return PropertyLoader.CONF.getDouble(key);
	}

	public static double getDouble(String key, double defaultValue) {
		return PropertyLoader.CONF.getDouble(key, defaultValue);
	}

	public static String getString(String key) {
		return PropertyLoader.CONF.getString(key);
	}

	public static String getString(String key, String defaultValue) {
		return PropertyLoader.CONF.getString(key, defaultValue);
	}

	/**
	 * 
	 * 用法演示。实际利用此代码片段时，可以直接去除main方法
	 */
	public static void main(String[] args) {
		System.out.println(PropertyLoader.getBoolean("boolean_key"));
		System.out.println(PropertyLoader.getInt("int_key"));

		// 注意：double精度会有损失
		System.out.println(PropertyLoader.getDouble("double_key"));
		System.out.println(PropertyLoader.getString("string_key"));

		System.out.println(PropertyLoader.getString("bad_key", "I am hjc"));
	}
}
