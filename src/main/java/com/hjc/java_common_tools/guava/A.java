package com.hjc.java_common_tools.guava;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.collect.Table;
import com.google.common.primitives.Ints;

public class A {

	public static void main(String[] args) {
		Map<String, Map<Long, List<String>>> map = Maps.newHashMap();
		System.out.println(map);
		Map<String, Map<String, List<Integer>>> map2 = Maps.newHashMap();
		System.out.println(map2);
		ImmutableList<Integer> list1 = ImmutableList.of(3, 4, 6);
		System.out.println(list1 instanceof List);
		System.out.println(Ints.toArray(list1).length);
		System.out.println(Ints.max(Ints.toArray(list1)));
		String string = CharMatcher.is('o').removeFrom(
				"some text 89983 and more");
		System.out.println(string);
		String[] subdirs = { "usr", "local", "lib" };
		System.out.println(Joiner.on('/').join(subdirs));
		HashSet<Integer> setA = Sets.newHashSet(1, 2, 3, 4, 5);
		HashSet<Integer> setB = Sets.newHashSet(4, 5, 6, 7, 8);
		SetView<Integer> bing = Sets.union(setA, setB);
		System.out.println("并集：" + bing);
		SetView<Integer> cha = Sets.difference(setA, setB);
		System.out.println("差集：" + cha);
		SetView<Integer> jiao = Sets.intersection(setA, setB);
		System.out.println("交集：" + jiao);
		System.out.println(Strings.emptyToNull("fda"));
		System.out.println(Strings.padEnd("何健超", 8, '0'));
		System.out.println(Strings.repeat("刘甜甜", 9));

		BiMap<String, String> aBiMap = HashBiMap.<String, String> create();
		aBiMap.put("abc", "def");
		aBiMap.put("defe", "abcd");
		System.out.println(aBiMap); // output: {abc=def, defe=abcd}
		System.out.println(aBiMap.get("abc")); // output: def
		// 这行为抛异常！
		// 因为"defe":"abcd"已存在,所以它会影响BiMap.inverse().
		// aBiMap.put("abc", "abcd");
		// 但我们可以用BiMap.forcePut()强制放入"abc":"abcd"
		// 放入的同时"defe":"abcd"也被删除了
		aBiMap.forcePut("abc", "abcd");
		System.out.println("--" + aBiMap);
		// 用BiMap.inverse()得到反转的Map.
		aBiMap = aBiMap.inverse();
		System.out.println(aBiMap); // output: {abcd=abc}
		System.out.println(aBiMap.get("abcd")); // output: abc

		System.out.println("-----");
		BiMap<String, String> biMap = HashBiMap.create();

		biMap.put("k1", "v1");
		biMap.put("k2", "v2");

		System.out.println("k1 = " + biMap.get("k1"));
		System.out.println("v2 = " + biMap.inverse().get("v2"));
		
		System.out.println("````````");
		Table<String, Integer, String> aTable = HashBasedTable.create();  
		 
        for (char a = 'A'; a <= 'C'; ++a) {  
            for (Integer b = 1; b <= 3; ++b) {   
                aTable.put(Character.toString(a), b, String.format("%c%d", a, b));  
            }  
        }  
        System.out.println(aTable);
        System.out.println(aTable.containsColumn(3));   
        System.out.println(aTable.columnMap()); 
        System.out.println(aTable.rowMap());   
        System.out.println(aTable.remove("B", 3)); 
        System.out.println(aTable);
	}
}
