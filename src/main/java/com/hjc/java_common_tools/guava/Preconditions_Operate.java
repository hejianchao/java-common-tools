package com.hjc.java_common_tools.guava;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.common.base.Preconditions;

/**
 * 最佳实践例子：
 * 
 * <li>
 * checkArgument(i >= 0, "Argument was %s but expected nonnegative", i);</li>
 * <li>
 * checkArgument(i < j, "Expected i < j, but %s > %s", i, j);</li> </p>
 * 
 * <p>
 * <a href=
 * "https://code.google.com/p/guava-libraries/wiki/PreconditionsExplained"
 * >api</a>
 * 
 * <pre>
 * 
 * 每种方法又对应了三种重载：
 * 
 * 1.没有错误信息
 * 2.有直接的错误信息
 * 3.带模板的错误信息
 * </pre>
 */
public class Preconditions_Operate {

	@Test
	public void Preconditions() {
		getPerson(8, "peida");
		getPerson(-9, "peida");
		getPerson(8, "");
		getPerson(8, null);

		getPersonByPrecondition(8, "peida");
		try {
			getPersonByPrecondition(-9, "peida");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			getPersonByPrecondition(8, "");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		try {
			getPersonByPrecondition(8, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 传统的参数校验方式.
	 * 
	 * 这里演示的校验规则为：age > 0 并且name 非空
	 */
	public static void getPerson(int age, String neme) {
		if (age > 0 && neme != null && neme.isEmpty() != true) {
			System.out.println("a person age:" + age + ",neme:" + neme);
		} else {
			System.out.println("参数输入有误！");
		}
	}

	public static void getPersonByPrecondition(int age, String neme) {
		Preconditions.checkArgument(age > 0, "age 必须大于0");

		// 此方法将会返回：the non-null reference that was validated
		Preconditions.checkNotNull(neme, "neme为null");

		Preconditions.checkArgument(neme.length() > 0, "neme为\'\'");
		System.out.println("a person age:" + age + ",neme:" + neme);
	}

	@Test
	public void testPreconditionsByMethod() {
		// 用作方法中检查参数
		Preconditions.checkArgument("".isEmpty());

		// 检查value不为null， 直接返回value
		Preconditions.checkNotNull("hello");

		// 检查对象的一些状态，不依赖方法参数
		Preconditions.checkState("someStat".contains("some"));

	}

	@Rule
	public ExpectedException exceptedException = ExpectedException.none();

	@Test
	public void testCheckElementIndex() {
		// checkElementIndex(int index, int
		// size)：index范围[0,size)。注意区别于checkPositionIndex
		Preconditions.checkElementIndex(2, 3);

		exceptedException.expect(IndexOutOfBoundsException.class);

		// 期望异常中包含这句话
		exceptedException.expectMessage("index (3) must be less than size (3)");
		Preconditions.checkElementIndex(3, 3);
	}

	@Test
	public void testCheckPositionIndex() {
		// checkPositionIndex(int index, int
		// size)：index范围[0,size]。注意区别于checkElementIndex
		Preconditions.checkPositionIndex(3, 3);
		Preconditions.checkPositionIndex(0, 3);

		exceptedException.expect(IndexOutOfBoundsException.class);

		// 期望异常中包含这句话
		exceptedException
				.expectMessage("index (100) must not be greater than size (3)");

		Preconditions.checkPositionIndex(100, 3);
	}

	@Test
	public void testCheckPositionIndexes() {
		// checkPositionIndexes(int start, int end, int
		// size)：检查[start, end)是一个长度为size的list，
		// string或array合法的范围子集，即：start<end<=size
		Preconditions.checkPositionIndexes(3, 6, 6);

		exceptedException.expect(IndexOutOfBoundsException.class);

		// 期望异常中包含这句话
		exceptedException
				.expectMessage("end index (7) must not be greater than size (6)");

		Preconditions.checkPositionIndexes(3, 7, 6);
	}
}
