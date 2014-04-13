package com.hjc.java_common_tools.guava;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Throwables;

public class B {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");

		System.out.println(list);

		List<String> unmodifiableList = Collections.unmodifiableList(list);

		System.out.println(unmodifiableList);

		List<String> unmodifiableList1 = Collections.unmodifiableList(Arrays
				.asList("a", "b", "c"));
		System.out.println(unmodifiableList1);

		String temp = unmodifiableList.get(1);
		System.out.println("unmodifiableList [1]：" + temp);

		list.add("baby");
		System.out.println("list add a item after list:" + list);
		System.out.println("list add a item after unmodifiableList:"
				+ unmodifiableList);

		try {
            throw new Exception("fdafsa");
        } catch (Throwable t) {
            String ss = Throwables.getStackTraceAsString(t);
            System.out.println("ss:"+ss);
            Throwables.propagate(t);
        }
		unmodifiableList1.add("bb");
		System.out.println("unmodifiableList add a item after list:"
				+ unmodifiableList1);

		unmodifiableList.add("cc");
		System.out.println("unmodifiableList add a item after list:"
				+ unmodifiableList);

	}

}
