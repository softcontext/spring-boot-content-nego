package com.example.web.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "id", "name", "company" })
public class User {

	private int id;
	private String name;
	private String company;

	public User() {}

	public User(int id, String name, String company) {
		this.id = id;
		this.name = name;
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getColumnCount() {
		return getClass().getDeclaredFields().length;
	}
}
