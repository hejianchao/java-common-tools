package com.hjc.java_common_tools.apache;

import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.primitives.Ints;

/**
 * 用Pair 来较好的封装tuple的用法。
 * 
 * Since: Lang 3.0
 */
public class ImmutablePair_Study {

	@Test
	public void testBasic() {
		String left = "name_hjc";
		Integer right = 25;
		ImmutablePair<String, Integer> immutablePair = new ImmutablePair<String, Integer>(
				left, right);
		Assert.assertEquals(left, immutablePair.getLeft());
		Assert.assertEquals(right.intValue(), immutablePair.getRight()
				.intValue());

		Assert.assertEquals("(name_hjc,25)", immutablePair.toString());
	}

	@Test
	public void testOthers() {
		ImmutablePair<String, Integer> immutablePair = ImmutablePair.of(
				"hello", 28);
		Assert.assertEquals("hello", immutablePair.getLeft());

		// getKey,getValue与Map中行为一致
		Assert.assertEquals("hello", immutablePair.getKey());
		Assert.assertEquals(28, immutablePair.getValue().intValue());
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	// 异常测试。ImmutablePair 不支持对值进行改变
	@Test
	public void testSetValue() {

		ImmutablePair<String, Integer> immutablePair = ImmutablePair.of(
				"hello", 28);
		expectedEx.expect(UnsupportedOperationException.class);
		immutablePair.setValue(45);
	}
}