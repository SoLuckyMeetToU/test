package com.lanou.entity;

public class Staff {
	private String name;
	private Integer age;
	private String sex;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Override
	public String toString() {
		return "Staff [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
	public Staff(String name, Integer age, String sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	} 
}
