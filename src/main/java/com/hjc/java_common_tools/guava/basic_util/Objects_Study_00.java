package com.hjc.java_common_tools.guava.basic_util;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Objects;

/**
 * 参考:http://ifeve.com/google-guava-commonobjectutilities/ <br />
 * http://www.cnblogs.com/peida/p/3136042.html
 * 
 */
public class Objects_Study_00 {

	@Test
	public void testEquals() {
		Assert.assertTrue(Objects.equal("a", "a"));
		Assert.assertFalse(Objects.equal(null, "a"));
		Assert.assertFalse(Objects.equal("a", null));
		Assert.assertTrue(Objects.equal(null, null));

		// 这里为false 是因为比较Person类的equals方法进行比较，而此处没有覆写其equals方法
		Assert.assertFalse(Objects.equal(new Person("hjc", 25), new Person(
				"hjc", 25)));
		Person person = new Person("peida", 25);
		Assert.assertTrue(Objects.equal(person, person));
	}

	/**
	 * 感觉还是没有Apache Common 中的ToStringBuilder
	 * 好用。这里需要人为手动去拼字符串，不过可以方便控制输出字段的顺序和刷选只输出哪些。
	 */
	@Test
	public void testToString() {
		Person person = new Person("peida", 25);
		Assert.assertEquals("Person{key1=value1}",
				Objects.toStringHelper(person).add("key1", "value1").toString());

		Assert.assertEquals(
				"Person{name=peida, age=25, key1=value1}",
				Objects.toStringHelper(person).add("name", person.name)
						.add("age", person.age).add("key1", "value1")
						.toString());

		Assert.assertEquals("Person{key1=value1, key2=null}", Objects
				.toStringHelper(person).add("key1", "value1").add("key2", null)
				.toString());
	}
}

class Person {
	public String name;
	public int age;

	Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

}
