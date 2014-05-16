package com.hjc.java_common_tools.json.gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.hjc.java_common_tools.json.OneBeanWhichFiledNotNormal;
import com.hjc.java_common_tools.json.OneBeanWithoutDefaultConstructor;
import com.hjc.java_common_tools.json.OneNormalBean;

/**
 * <pre>
 * // 注意这里的Gson的构建方式为GsonBuilder,区别于test1中的Gson gson = new Gson();
 * Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation() // 不导出实体中没有用@Expose注解的属性
 * 		.enableComplexMapKeySerialization() // 支持Map的key为复杂对象的形式
 * 		.serializeNulls().setDateFormat(&quot;yyyy-MM-dd HH:mm:ss:SSS&quot;)// 时间转化为特定格式
 * 		.setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)// 会把字段首字母大写,注:对于实体上使用了@SerializedName注解的不会生效.
 * 		.setPrettyPrinting() // 对json结果格式化.
 * 		.setVersion(1.0) // 有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
 * 							// @Since(版本号)能完美地实现这个功能.还的字段可能,随着版本的升级而删除,那么
 * 							// @Until(版本号)也能实现这个功能,GsonBuilder.setVersion(double)方法需要调用.
 * 		.create();
 * 
 * </pre>
 */
public class GsonTest {

	/**
	 * 验证规范的Bean的json-bean转换
	 */
	@Test
	public void testBasic() {
		Gson gson = new Gson();
		OneNormalBean bean1 = new OneNormalBean("hjc-1", "浙江省杭州市-1", 25, true);
		OneNormalBean bean2 = new OneNormalBean("hjc-2", "浙江省杭州市-2", 26, false);
		List<OneNormalBean> beans = new ArrayList<OneNormalBean>();
		beans.add(bean1);
		beans.add(bean2);
		System.out.println("bean1-->" + gson.toJson(bean1));

		String beansStr = gson.toJson(beans);
		System.out.println("beans-->" + beansStr);

		// 对于数组，指定其反解析类型
		Type listType = new TypeToken<List<OneNormalBean>>() {
		}.getType();

		List<OneNormalBean> newBeans = gson.fromJson(beansStr, listType);
		Assert.assertEquals(newBeans.get(0).getName(), "hjc-1");
		Assert.assertEquals(newBeans.get(0).getAddress(), "浙江省杭州市-1");
		Assert.assertEquals(newBeans.get(0).getAge(), 25);
		Assert.assertEquals(newBeans.get(0).isOk(), true);

		Assert.assertEquals(newBeans.get(1).getName(), "hjc-2");
		Assert.assertEquals(newBeans.get(1).getAddress(), "浙江省杭州市-2");
		Assert.assertEquals(newBeans.get(1).getAge(), 26);
		Assert.assertEquals(newBeans.get(1).isOk(), false);
	}

	/**
	 * 验证无默认构造方法的Bean的json-bean转换(注意：fastjson 会抛出异常)
	 */
	@Test
	public void testBasic_01() {
		Gson gson = new Gson();
		OneBeanWithoutDefaultConstructor bean1 = new OneBeanWithoutDefaultConstructor(
				"hjc-1", "浙江省杭州市-1", 25, true);
		OneBeanWithoutDefaultConstructor bean2 = new OneBeanWithoutDefaultConstructor(
				"hjc-2", "浙江省杭州市-2", 26, false);
		List<OneBeanWithoutDefaultConstructor> beans = new ArrayList<OneBeanWithoutDefaultConstructor>();
		beans.add(bean1);
		beans.add(bean2);
		System.out.println("bean1-->" + gson.toJson(bean1));

		String beansStr = gson.toJson(beans);
		System.out.println("beans-->" + beansStr);

		// 对于数组，指定其反解析类型
		Type listType = new TypeToken<List<OneBeanWithoutDefaultConstructor>>() {
		}.getType();

		// 尽管OneBeanWithoutDefaultConstructor 缺少无参数构造方法，但是仍然能够正常反解析
		List<OneBeanWithoutDefaultConstructor> newBeans = gson.fromJson(
				beansStr, listType);
		Assert.assertEquals(newBeans.get(0).getName(), "hjc-1");
		Assert.assertEquals(newBeans.get(0).getAddress(), "浙江省杭州市-1");
		Assert.assertEquals(newBeans.get(0).getAge(), 25);
		Assert.assertEquals(newBeans.get(0).isOk(), true);

		Assert.assertEquals(newBeans.get(1).getName(), "hjc-2");
		Assert.assertEquals(newBeans.get(1).getAddress(), "浙江省杭州市-2");
		Assert.assertEquals(newBeans.get(1).getAge(), 26);
		Assert.assertEquals(newBeans.get(1).isOk(), false);
	}

	/**
	 * 验证Filed 有一个字段命名不规范（正常应该命名为address的，现在特意命名为aDdress）的Bean的json-bean转换
	 */
	@Test
	public void testBasic_02() {
		Gson gson = new Gson();
		OneBeanWhichFiledNotNormal bean1 = new OneBeanWhichFiledNotNormal(
				"hjc-1", "浙江省杭州市-1", 25, true);
		OneBeanWhichFiledNotNormal bean2 = new OneBeanWhichFiledNotNormal(
				"hjc-2", "浙江省杭州市-2", 26, false);
		List<OneBeanWhichFiledNotNormal> beans = new ArrayList<OneBeanWhichFiledNotNormal>();
		beans.add(bean1);
		beans.add(bean2);
		System.out.println("bean1-->" + gson.toJson(bean1));

		String beansStr = gson.toJson(beans);
		System.out.println("beans-->" + beansStr);

		// 对于数组，指定其反解析类型
		Type listType = new TypeToken<List<OneBeanWhichFiledNotNormal>>() {
		}.getType();

		List<OneBeanWhichFiledNotNormal> newBeans = gson.fromJson(beansStr,
				listType);
		Assert.assertEquals(newBeans.get(0).getName(), "hjc-1");

		// fastjson 是根据get方法反解析，而gson是根据Field本身名称反解析的
		Assert.assertEquals(newBeans.get(0).getaDdress(), "浙江省杭州市-1");
		Assert.assertEquals(newBeans.get(0).getAge(), 25);
		Assert.assertEquals(newBeans.get(0).isOk(), true);

		Assert.assertEquals(newBeans.get(1).getName(), "hjc-2");
		Assert.assertEquals(newBeans.get(1).getaDdress(), "浙江省杭州市-2");
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
		Gson gson = new Gson();

		String jsonString = gson.toJson(list);
		System.out.println("json字符串:" + jsonString);
		// 解析json字符串
		List<Map<String, Object>> list2 = gson.fromJson(jsonString,
				new TypeToken<List<Map<String, Object>>>() {
				}.getType());
		System.out.println(list2);

		// 打印出对人友好的json格式
		System.out.println(new GsonBuilder().setPrettyPrinting().create()
				.toJson(list));
	}
}
