package com.hjc.java_common_tools.guava;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;

/**
 * http://ifeve.com/google-guava-strings/
 * 
 * @author hejianchao
 * 
 */
public class String_Operate {

	/**
	 * 验证{@link Joiner}的基本操作
	 */
	@Test
	public void testJoiner() {
		Joiner joiner = Joiner.on(" - ").skipNulls();

		Assert.assertEquals("he - jian - chao",
				joiner.join("he", null, "jian", "chao"));

		Assert.assertEquals("1,5,7", Joiner.on(",")
				.join(Arrays.asList(1, 5, 7)));
	}

	/**
	 * 验证{@link Strings}的基本操作
	 */
	@Test
	public void testStrings() {
		Assert.assertEquals("test", Strings.emptyToNull("test"));
		Assert.assertEquals(" ", Strings.emptyToNull(" "));
		Assert.assertNull(Strings.emptyToNull(""));
		Assert.assertNull(Strings.emptyToNull(null));

		Assert.assertFalse(Strings.isNullOrEmpty("test"));
		Assert.assertFalse(Strings.isNullOrEmpty(" "));
		Assert.assertTrue(Strings.isNullOrEmpty(""));
		Assert.assertTrue(Strings.isNullOrEmpty(null));

		Assert.assertEquals("test", Strings.nullToEmpty("test"));
		Assert.assertEquals(" ", Strings.nullToEmpty(" "));
		Assert.assertEquals("", Strings.nullToEmpty(""));
		Assert.assertEquals("", Strings.nullToEmpty(null));

		Assert.assertEquals("Ralph_____", Strings.padEnd("Ralph", 10, '_'));
		Assert.assertEquals("Bob!!!!!!!", Strings.padEnd("Bob", 10, '!'));

		Assert.assertEquals("^^^^^Ralph", Strings.padStart("Ralph", 10, '^'));
		Assert.assertEquals("_______Bob", Strings.padStart("Bob", 10, '_'));

		Assert.assertEquals("xyxyxyxyxy", Strings.repeat("xy", 5));

		Assert.assertEquals("abc", Strings.commonPrefix("abcdefg", "abchello"));
		Assert.assertEquals("你好，", Strings.commonPrefix("你好，何健超", "你好，世界"));

		Assert.assertEquals("，你好", Strings.commonSuffix("何健超，你好", "世界，你好"));
		Assert.assertEquals("c", Strings.commonSuffix("aabbcc", "aa__c"));
	}

	public static void main(String[] args) {
		Joiner joiner = Joiner.on(',');
		joiner.skipNulls(); // does nothing!
		String s = joiner.skipNulls().join("wrong", null, "wrong");
		System.out.println(s);

		ArrayList<String> stringList = Lists.newArrayList();
		ArrayList<Integer> intList = Lists.newArrayList();
		System.out.println(stringList.getClass().isAssignableFrom(
				intList.getClass()));
		
	}
}
