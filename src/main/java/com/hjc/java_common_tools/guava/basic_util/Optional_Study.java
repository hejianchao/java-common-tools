package com.hjc.java_common_tools.guava.basic_util;

import java.util.HashSet;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Optional;

/**
 * <p />
 * 参考: http://ifeve.com/google-guava-using-and-avoiding-null/
 * 
 * <p />
 * 最佳实践： 对返回值，采用Optional。对方法入参可以不限制。（试想：如果别人调用你的方法，还需要在调用前封装为Optional类型，
 * 会抓狂吧？不过话说返回Optional类型，用户也要if/else一下了）
 * 
 * <p />
 * Optional的最大价值，在于时刻提醒我们注意，入参和返回值，null 所表示的真正含义。
 * Optional除了给null值命名所带来的代码可阅读性的提高
 * ，最大的好处莫过于Optional是傻瓜式的。Optional对象的使用强迫你去积极的思考这样一种情况
 * ，如果你想让你的程序返回null值，这null值代表的含义是什么，因为你想要取得返回值，必然从Optional对象内部去获得，所以你必然会这么去思考。
 * 但是只是简单的使用一个Null值会很轻易的让人忘记去思索代码所要表达的含义到底是什么
 * ，尽管FindBugs有些帮助，但是我们还是认为它并没有尽可能的解决好帮助程序员去思索null值代表的含义这个问题。
 * 　　这种思考会在你返回某些存在的值或者不存在的值的时候显得特别相关
 * 。和其他人一样，你绝对很可能会忘记别人写的方法method(a,b)可能会返回一个null值
 * ，就好像当你去写method(a,b)的实现时，你也很可能忘记输入参数a也可以是null
 * 。如果返回的是Optional对象，对于调用者来说，就可以忘却怎么去度量null代表的是什么含义
 * ，因为他们始终要从optional对象中去获得真正的返回值。
 * 
 * <p />
 * null在java中可以标识一个不确定的对象，同时在很多个集合中它代表key不存在，也可以代表值为null。 Guava 的Optional
 * 为我们提供了一个抽象类来解决这个问题。用类Present和Absent代表值存在和不存在。
 * 
 */
public class Optional_Study {

	@Test
	public void testPresent() {
		Optional<Integer> possible = Optional.of(5);
		Assert.assertTrue(possible.isPresent());

		Assert.assertEquals(possible.get().intValue(), 5);
		Assert.assertEquals(possible.or(100).intValue(), 5);
		Assert.assertEquals(possible.or(100).intValue(), 5);
	}

	@Test
	public void testAbsent() {
		Optional<Integer> absentValue = Optional.absent();

		Assert.assertFalse(absentValue.isPresent());
		Assert.assertEquals(absentValue.or(-1).intValue(), -1);
		Assert.assertEquals(absentValue.orNull(), null);
		Assert.assertEquals(absentValue.asSet(), new HashSet<Integer>());
	}

	@Test
	public void testFromNullable() {
		Optional<Integer> maybeNull = Optional.fromNullable(null);

		Assert.assertFalse(maybeNull.isPresent());
		Assert.assertEquals(maybeNull.or(-1).intValue(), -1);
		Assert.assertEquals(maybeNull.orNull(), null);
		Assert.assertEquals(maybeNull.asSet(), new HashSet<Integer>());
	}
}
