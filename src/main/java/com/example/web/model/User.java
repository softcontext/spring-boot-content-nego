package com.example.web.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

@Data
@XmlRootElement(name = "user")
@XmlType(propOrder = { "id", "name", "company" })
public class User {

	private int id;
	private String name;
	private String company;

	public User() {
	}

	public User(int id, String name, String company) {
		this.id = id;
		this.name = name;
		this.company = company;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	@XmlElement
	public void setCompany(String company) {
		this.company = company;
	}

	public int getColumnCount() {
		return this.getClass().getDeclaredFields().length;
	}
}
