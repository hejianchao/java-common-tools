package com.hjc.java_common_tools.json;

/**
 * 这里Normal 表示的含义是：是最普通的Java Bean，有无参数构造方法，Filed 命名都为小写
 * 
 */
public class OneBeanWhichFiledNotNormal {
	private String name;
	private String aDdress;
	private int age;
	private boolean ok;

	public OneBeanWhichFiledNotNormal() {
	}

	public OneBeanWhichFiledNotNormal(String name, String address, int age,
			boolean ok) {
		this.name = name;
		this.aDdress = address;
		this.age = age;
		this.ok = ok;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getaDdress() {
		return aDdress;
	}

	public void setaDdress(String aDdress) {
		this.aDdress = aDdress;
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
