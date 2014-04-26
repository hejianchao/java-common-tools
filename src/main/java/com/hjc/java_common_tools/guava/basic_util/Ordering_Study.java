package com.hjc.java_common_tools.guava.basic_util;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

/**
 * 参考:http://www.cnblogs.com/peida/p/Guava_Ordering.html TODO 还需要进一步完善
 */
public class Ordering_Study {

	@Test
	public void test() {
		Ordering<String> naturalOrdering = Ordering.natural();
		Assert.assertEquals(-1, naturalOrdering.compare("cc", "dd"));

		Ordering<String> byLengthOrdering = new Ordering<String>() {
			public int compare(String left, String right) {
				return Ints.compare(left.length(), right.length());
			}
		};

		List<String> aList = Arrays.asList("a", "aa", "aaa", "b", "bb", "bbb");
		Assert.assertEquals("[aaa, bbb, aa]",
				byLengthOrdering.greatestOf(aList.iterator(), 3).toString());

	}
}
