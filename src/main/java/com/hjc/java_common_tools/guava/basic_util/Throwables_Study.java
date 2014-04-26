package com.hjc.java_common_tools.guava.basic_util;

import java.io.InputStream;
import java.net.URL;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.base.Throwables;

/**
 * 参考:http://ifeve.com/google-guava-throwables/ <br />
 * http://www.cnblogs.com/peida/p/3160618.html <br />
 * TODO 还需要继续深入
 * 
 */
public class Throwables_Study {

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Test
	public void testA() {
		expectedException.expect(RuntimeException.class);
		expectedException
				.expectMessage("java.net.UnknownHostException: hejianchao.test_hello");
		try {
			URL url = new URL("http://hejianchao.test_hello");
			final InputStream in = url.openStream();
			in.close();
		} catch (Throwable t) {
			// 注意此处打印的错误信息详情
			String errorDetail = Throwables.getStackTraceAsString(t);
			System.out.println(errorDetail);

			throw Throwables.propagate(t);
		}
	}
}
