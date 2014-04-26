package com.hjc.java_common_tools.guava.basic_util.prims;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.primitives.Ints;

/**
 * 
 * 参考:http://ifeve.com/google-guava-primitives/
 */
public class Ints_Study {

	@Test
	public void testBasic() {
		int[] aArray = { 3, 5, 7, 9 };

		// 把数组转为相应包装类的List
		List<Integer> aList = Ints.asList(aArray);
		Assert.assertEquals(aArray.length, aList.size());
		Assert.assertTrue(aList instanceof List);

		List<Integer> bList = new ArrayList<Integer>(aList);
		// 把集合拷贝为数组，和collection.toArray()一样线程安全
		int[] bArray = Ints.toArray(bList);
		Assert.assertEquals(bList.size(), bArray.length);

		// 串联多个原生类型数组
		int[] aAndBArray = Ints.concat(aArray, bArray);
		Assert.assertEquals(aArray.length + bArray.length, aAndBArray.length);
		Assert.assertEquals("[3, 5, 7, 9, 3, 5, 7, 9]",
				Arrays.toString(aAndBArray));

		// 判断原生类型数组是否包含给定值
		Assert.assertTrue(Ints.contains(aAndBArray, 5));
		Assert.assertFalse(Ints.contains(aAndBArray, 555));

		// 给定值在数组中首次出现处的索引，若不包含此值返回-1
		Assert.assertEquals(1, Ints.indexOf(aAndBArray, 5));
		Assert.assertEquals(-1, Ints.indexOf(aAndBArray, 555));

		// 给定值在数组最后出现的索引，若不包含此值返回-1
		Assert.assertEquals(5, Ints.lastIndexOf(aAndBArray, 5));
		Assert.assertEquals(-1, Ints.lastIndexOf(aAndBArray, 555));

		// 数组中最小（大）的值
		Assert.assertEquals(3, Ints.min(aAndBArray));
		Assert.assertEquals(9, Ints.max(aAndBArray));

		// 把数组用给定分隔符连接为字符串
		Assert.assertEquals("3,5,7,9,3,5,7,9", Ints.join(",", aAndBArray));
	}
}
