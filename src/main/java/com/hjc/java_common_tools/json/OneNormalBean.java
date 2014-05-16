package com.hjc.java_common_tools.json;

/**
 * 这里Normal 表示的含义是：是最普通的Java Bean，具有默认的无参数构造方法，Filed 命名也遵守规范（强烈不建议Bean
 * Field第一、第二个字符大写！不然fastjson 可能会有坑！）
 * 
 */
public class OneNormalBean {
	private String name;
	private String address;
	private int age;
	private boolean ok;

	public OneNormalBean() {
	}

	public OneNormalBean(String name, String address, int age, boolean ok) {
		this.name = name;
		this.address = address;
		this.age = age;
		this.ok = ok;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

}
