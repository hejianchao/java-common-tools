package com.hjc.java_common_tools.guava;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;

public class Todo {

	public static void main(String[] args) {
		ImmutableList<String> of = ImmutableList.of("a", "b", "c", "d");
		ImmutableList<String> test = ImmutableList.of();
		System.out.println(of);
		System.out.println(test);
		ImmutableMap<String, String> map = ImmutableMap.of("key1", "value1",
				"key2", "value2");
		System.out.println(map);

		// File file = new File("test.txt");
		System.out.println(Thread.currentThread().getClass()
				.getResource("/test.txt"));
		File file = new File(Thread.currentThread().getClass()
				.getResource("/test.txt").getFile());
		List<String> lines = null;
		try {
			lines = Files.readLines(file, Charsets.UTF_8);
			System.out.println(lines);
		} catch (IOException e) {
			e.printStackTrace();
		}

		int[] numbers = { 1, 2, 3, 4, 5 };
		System.out.println(Ints.contains(numbers, 5));
		String numbersAsString = Joiner.on(";").join(Ints.asList(numbers));
		System.out.println(numbersAsString);
		System.out.println(Ints.join("fdafda", numbers));
		System.out.println(Joiner.on("fdafda").join(Ints.asList(numbers)));

		String testString = "foo , what,,,more,";
		Iterable<String> split = Splitter.on(",").omitEmptyStrings()
				.trimResults().split(testString);
		System.out.println(split.toString());
	}

}
