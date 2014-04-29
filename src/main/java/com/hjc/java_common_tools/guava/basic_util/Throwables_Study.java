package com.hjc.java_common_tools.guava.basic_util;

import java.io.IOException;

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
	public ExpectedException thrown = ExpectedException.none();

	@Test
	public void testThrowables() {
		thrown.expect(RuntimeException.class);
		try {
			throw new Exception();
		} catch (Throwable t) {
			String ss = Throwables.getStackTraceAsString(t);
			System.out.println("error msg:" + ss);
			Throwables.propagate(t);
		}
	}

	@Test
	public void call() throws IOException {
		thrown.expect(IOException.class);
		try {
			throw new IOException();
		} catch (Throwable t) {
			Throwables.propagateIfInstanceOf(t, IOException.class);

			// 不会执行throw语句，因为上一句匹配到了IOException，所以已经抛出了异常。
			throw Throwables.propagate(t);
		}
	}

	@Test
	public void call2() throws IOException {
		thrown.expect(RuntimeException.class);
		try {
			throw new IOException();
		} catch (Throwable t) {
			Throwables.propagateIfInstanceOf(t, RuntimeException.class);

			// 会执行throw语句
			throw Throwables.propagate(t);
		}
	}
}
