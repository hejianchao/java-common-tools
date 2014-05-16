package com.hjc.java_common_tools.json.fastjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.hjc.java_common_tools.json.OneBeanWhichFiledNotNormal;
import com.hjc.java_common_tools.json.OneBeanWithoutDefaultConstructor;
import com.hjc.java_common_tools.json.OneNormalBean;

/**
 * FastJson 是阿里巴巴自身开源的一套高效json处理器。使用非常简单，不过如下的坑需要时刻小心：
 * <p>
 * FastJson处理的bean从Field到构造方法，都要符合Java Bean
 * 规范，具体来说是：Field命名最好前两个字母不要大写；提供默认无参数构造函数；提供get/set方法（因为fastjson基于get方法进行解析）
 * 
 * <p>
 * 
 * <pre>
 * public static final Object parse(String text); 								// 把JSON文本解析为JSONObject或者JSONArray
 * public static final JSONObject parseObject(String text)；		 				// 把JSON文本解析为JSONObject
 * public static final <T> T parseObject(String text, Class<T> clazz); 			// 把JSON文本解析为JavaBean
 * public static final JSONArray parseArray(String text); 						// 把JSON文本解析为JSONArray
 * public static final <T> List<T> parseArray(String text, Class<T> clazz); 	// 把JSON文本解析为JavaBean集合
 * public static final String toJSONString(Object object); 						// 把JavaBean序列化为JSON文本
 * public static final String toJSONString(Object object, boolean prettyFormat);// 把JavaBean序列化为带格式的JSON文本
 * public static final Object toJSON(Object javaObject); 						// 把JavaBean转换为JSONObject或者JSONArray
 * </pre>
 * 
 */
public class FastJsonTest {

	/**
	 * 验证规范的Bean的json-bean转换
	 */
	@Test
	public void testBasic() {
		OneNormalBean bean1 = new OneNormalBean("hjc-1", "浙江省杭州市-1", 25, true);
		OneNormalBean bean2 = new OneNormalBean("hjc-2", "浙江省杭州市-2", 26, false);

		List<OneNormalBean> beans = new ArrayList<OneNormalBean>();
		beans.add(bean1);
		beans.add(bean2);
		System.out.println("bean1-->" + JSON.toJSONString(bean1));

		String beansStr = JSON.toJSONString(beans);
		System.out.println("beans-->" + JSON.toJSONString(beans));

		List<OneNormalBean> newBeans = JSON.parseArray(beansStr,
				OneNormalBean.class);
		Assert.assertEquals(newBeans.get(0).getName(), "hjc-1");
		Assert.assertEquals(newBeans.get(0).getAddress(), "浙江省杭州市-1");
		Assert.assertEquals(newBeans.get(0).getAge(), 25);
		Assert.assertEquals(newBeans.get(0).isOk(), true);

		Assert.assertEquals(newBeans.get(1).getName(), "hjc-2");
		Assert.assertEquals(newBeans.get(1).getAddress(), "浙江省杭州市-2");
		Assert.assertEquals(newBeans.get(1).getAge(), 26);
		Assert.assertEquals(newBeans.get(1).isOk(), false);
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	/**
	 * 验证无默认构造方法的Bean的json-bean转换(注意：fastjson 会抛出异常)
	 */
	@Test
	public void testBasic_01() {
		OneBeanWithoutDefaultConstructor bean1 = new OneBeanWithoutDefaultConstructor(
				"hjc-1", "浙江省杭州市-1", 25, true);
		OneBeanWithoutDefaultConstructor bean2 = new OneBeanWithoutDefaultConstructor(
				"hjc-2", "浙江省杭州市-2", 26, false);

		List<OneBeanWithoutDefaultConstructor> beans = new ArrayList<OneBeanWithoutDefaultConstructor>();
		beans.add(bean1);
		beans.add(bean2);
		System.out.println("bean1-->" + JSON.toJSONString(bean1));

		String beansStr = JSON.toJSONString(beans);
		System.out.println("beans-->" + JSON.toJSONString(beans));

		expectedEx.expect(JSONException.class);
		expectedEx.expectMessage("default constructor not found");
		// 将会抛异常：因为OneBeanWithoutDefaultConstructor 类没有无参数默认构造方法
		List<OneBeanWithoutDefaultConstructor> newBeans = JSON.parseArray(
				beansStr, OneBeanWithoutDefaultConstructor.class);
	}

	/**
	 * 验证Filed 有一个字段命名不规范（正常应该命名为address的，现在特意命名为aDdress）的Bean的json-bean转换
	 */
	@Test
	public void testBasic_02() {
		OneBeanWhichFiledNotNormal bean1 = new OneBeanWhichFiledNotNormal(
				"hjc-1", "浙江省杭州市-1", 25, true);
		OneBeanWhichFiledNotNormal bean2 = new OneBeanWhichFiledNotNormal(
				"hjc-2", "浙江省杭州市-2", 26, false);

		List<OneBeanWhichFiledNotNormal> beans = new ArrayList<OneBeanWhichFiledNotNormal>();
		beans.add(bean1);
		beans.add(bean2);
		System.out.println("bean1-->" + JSON.toJSONString(bean1));

		String beansStr = JSON.toJSONString(beans);
		System.out.println("beans-->" + JSON.toJSONString(beans));

		List<OneBeanWhichFiledNotNormal> newBeans = JSON.parseArray(beansStr,
				OneBeanWhichFiledNotNormal.class);
		Assert.assertEquals(newBeans.get(0).getName(), "hjc-1");

		// 注意，此处值为null
		Assert.assertEquals(newBeans.get(0).getaDdress(), null);
		Assert.assertEquals(newBeans.get(0).getAge(), 25);
		Assert.assertEquals(newBeans.get(0).isOk(), true);

		Assert.assertEquals(newBeans.get(1).getName(), "hjc-2");

		// 注意，此处值为null
		Assert.assertEquals(newBeans.get(1).getaDdress(), null);
		Assert.assertEquals(newBeans.get(1).getAge(), 26);
		Assert.assertEquals(newBeans.get(1).isOk(), false);
	}

	// 把json字符串转化成List<Map<String,Object>>对象
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("key1", 1);
		map2.put("key2", 2);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		list.add(map);
		list.add(map2);
		String jsonString = JSON.toJSONString(list);
		System.out.println("json字符串:" + jsonString);
		// 解析json字符串
		List<Map<String, Object>> list2 = JSON.parseObject(jsonString,
				new TypeReference<List<Map<String, Object>>>() {
				});
		System.out.println(list2);
	}
}
