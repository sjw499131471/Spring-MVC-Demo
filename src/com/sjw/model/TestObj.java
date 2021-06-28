package com.sjw.model;

import java.util.Date;

public class TestObj {
	private String id;
	private String name;
	private Integer sex;
	private String hobbit;
	private Date birthday;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getName () {
		return name;
	}
	public void setName (String name) {
		this.name = name;
	}
	public Date getBirthday () {
		return birthday;
	}
	public void setBirthday (Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the sex
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * @return the hobbit
	 */
	public String getHobbit() {
		return hobbit;
	}
	/**
	 * @param hobbit the hobbit to set
	 */
	public void setHobbit(String hobbit) {
		this.hobbit = hobbit;
	}
}
