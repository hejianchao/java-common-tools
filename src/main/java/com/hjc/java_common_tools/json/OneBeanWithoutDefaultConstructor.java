package com.hjc.java_common_tools.json;

public class OneBeanWithoutDefaultConstructor {
	private String name;
	private String address;
	private int age;
	private boolean ok;

	public OneBeanWithoutDefaultConstructor(String name, String address, int age,boolean ok) {
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
