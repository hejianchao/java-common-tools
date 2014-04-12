package com.hjc.java_common_tools.guava;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Objects;

public class Objects_Operate {

	//TODO: http://www.cnblogs.com/peida/p/3136042.html
	@Test
	public void testEquals() {
		Assert.assertTrue(Objects.equal("a", "a"));
		Assert.assertFalse(Objects.equal(null, "a"));
		Assert.assertFalse(Objects.equal("a", null));
		Assert.assertTrue(Objects.equal(null, null));

		Assert.assertFalse(Objects.equal(new Person("peida", 23), new Person(
				"peida", 23)));
		Person person = new Person("peida", 23);
		Assert.assertTrue(Objects.equal(person, person));
	}

	@Test
	public void testT() {
		String[] b = ",a,,b,".split(",");
		System.out.println(Objects.toStringHelper(b).add("b",
				Arrays.toString(b)));
		List<String> c = Arrays.asList("one", "tow");
		System.out.println(c);

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
