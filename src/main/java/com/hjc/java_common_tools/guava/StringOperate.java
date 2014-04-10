package com.hjc.java_common_tools.guava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * http://ifeve.com/google-guava-strings/
 * 
 * @author hejianchao
 * 
 */
public class StringOperate {

	@Test
	public void testJoiner() {
		Joiner joiner = Joiner.on(" - ").skipNulls();
		String joinResult = joiner.join("he", null, "jian", "chao");
		String[] expecteds = new String[] { "he - jian - chao" };
		String[] actuals = new String[] { joinResult };

		Assert.assertArrayEquals(expecteds, actuals);

		String joinRusult2 = Joiner.on(",").join(Arrays.asList(1, 5, 7));
		String[] expecteds2 = new String[] { "1,5,7" };
		String[] actuals2 = new String[] { joinRusult2 };

		Assert.assertArrayEquals(expecteds2, actuals2);
	}

	public static void main(String[] args) {
		Joiner joiner = Joiner.on(',');
		joiner.skipNulls(); // does nothing!
		String s = joiner.skipNulls().join("wrong", null, "wrong");
		System.out.println(s);
		String[] b = ",a,,b,".split(",");
		System.out.println(Objects.toStringHelper(b).add("b",
				Arrays.toString(b)));
		List<String> c = Arrays.asList("one", "tow");
		System.out.println(c);

		ArrayList<String> stringList = Lists.newArrayList();
		ArrayList<Integer> intList = Lists.newArrayList();
		System.out.println(stringList.getClass().isAssignableFrom(
				intList.getClass()));
	}
}
