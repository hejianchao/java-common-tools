package com.hjc.java_common_tools.guava;

import java.util.concurrent.Callable;

import org.junit.Test;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * http://www.cnblogs.com/peida/p/Guava_Cache.html
 * 
 * http://blog.csdn.net/desilting/article/details/11768817
 */
public class Cache_Study {

	@Test
	public void TestLoadingCache() throws Exception {
		LoadingCache<String, String> cahceBuilder = CacheBuilder.newBuilder()
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						String strProValue = "hello " + key + "!";
						return strProValue;
					}
				});

		System.out.println("jerry value:" + cahceBuilder.get("jerry"));
		System.out.println("jerry value:" + cahceBuilder.get("jerry"));
		System.out.println("peida value:" + cahceBuilder.get("peida"));
		System.out.println("peida value:" + cahceBuilder.get("peida"));
		System.out.println("lisa value:" + cahceBuilder.get("lisa"));
		cahceBuilder.put("harry", "ssdded");
		System.out.println("harry value:" + cahceBuilder.get("harry"));
	}

	@Test
	public void testcallableCache() throws Exception {
		Cache<String, String> cache = CacheBuilder.newBuilder()
				.maximumSize(1000).build();
		String resultVal = cache.get("jerry", new Callable<String>() {
			public String call() {
				String strProValue = "hello " + "jerry" + "!";
				return strProValue;
			}
		});
		System.out.println("jerry value : " + resultVal);

		Callable<String> callable = new Callable<String>() {
			public String call() {
				String strProValue = "hello " + "peida" + "!";
				return strProValue;
			}
		};
		resultVal = cache.get("peida", callable);
		System.out.println("peida value : " + resultVal);
		System.out.println("peida value : " + cache.get("peida", callable));
	}
}
