package com.hjc.java_common_tools.guava;

import org.junit.Test;

import com.google.common.base.Optional;

public class Optional_Operate {

	@Test
	public void testPresent() {
		Optional<Integer> possible = Optional.of(5);
		possible.isPresent(); // returns true
		possible.get(); // returns 5
	}
}
