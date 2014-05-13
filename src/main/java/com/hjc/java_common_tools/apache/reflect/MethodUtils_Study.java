package com.hjc.java_common_tools.apache.reflect;

import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;

public class MethodUtils_Study {

	@Test
	public void testFastDateFormatBaisc() {
		Class c = null;
		try {
			c = Class
					.forName("com.hjc.java_common_tools.apache.reflect.Sample");
			
			Method b = MethodUtils.getMatchingAccessibleMethod(c, "getValue", (Class<?>[])null);
			System.out.println(b);
			
			Object o = c.newInstance();
			System.out.println(o);
			String value = (String) MethodUtils.invokeMethod(o, "getValue",
					(Object[]) null);
			System.out.println("Results from getValue: " + value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Sample {

	public String getValue() {
		return "value";
	}

}
