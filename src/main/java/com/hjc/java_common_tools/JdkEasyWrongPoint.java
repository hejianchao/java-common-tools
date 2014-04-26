package com.hjc.java_common_tools;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.rules.ExpectedException;

/**
 * 个人总结的JDK中容易用错的api。
 * 
 */
public class JdkEasyWrongPoint {

	public static void main(String[] args) {
	}

	@Test
	public void testBoolean() {
		// 坑：Returns true if and only if the system property named by the
		// argument exists and is equal to the string "true".
		Assert.assertFalse(Boolean.getBoolean("true"));
	}

	/**
	 * 对数字运用要保持敏感：涉及数字计算就要考虑溢出；涉及除法就要考虑被除数是0；实在容纳不下了可以考虑BigDecimal之类。
	 */
	@Test
	public void testInteger() {
		// 坑：溢出
		long x = Integer.MAX_VALUE + 1;
		Assert.assertTrue(x < 0);
		long y = Integer.MAX_VALUE + (long) 1;
		Assert.assertTrue(y > 0);
	}

	@Test
	public void testSplit() {
		/**
		 * 坑：String的split，第二个参数为-1，会根据字符串中的分隔符，一直切分到底。
		 * 默认切分方式是第二个参数为0，则切分到分隔符后端为空值时，就切分结束了。
		 */
		String str1 = ",a,,";

		String[] splitResult0 = str1.split(",");
		Assert.assertEquals(2, splitResult0.length);
		Assert.assertEquals("[, a]", Arrays.toString(splitResult0));

		String[] splitResult1 = str1.split(",", -1);
		Assert.assertEquals(4, splitResult1.length);
		Assert.assertEquals("[, a, , ]", Arrays.toString(splitResult1));

		// 对比str1，仅仅是分隔符后多了感叹号，这样split参数为0，和-1效果一样。
		String str2 = ",a,,!";

		String[] split2Result0 = str2.split(",");
		Assert.assertEquals(4, split2Result0.length);
		Assert.assertEquals("[, a, , !]", Arrays.toString(split2Result0));

		String[] split2Result1 = str2.split(",", -1);
		Assert.assertEquals(4, split2Result1.length);
		Assert.assertEquals("[, a, , !]", Arrays.toString(split2Result1));
	}

	@Rule
	public ExpectedException excepteEX = ExpectedException.none();

	@Test
	public void testAsList() {
		/**
		 * ArrayList.asList返回的List是不可变的。add,remove等都会抛出异常
		 */
		List<String> aList = Arrays.asList("one", "tow");
		Assert.assertEquals("java.util.Arrays$ArrayList", aList.getClass()
				.getName());
		excepteEX.expect(UnsupportedOperationException.class);
		aList.add("hjc");
	}
}
