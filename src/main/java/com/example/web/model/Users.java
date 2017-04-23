package com.example.web.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement(name = "users-info")
public class Users {

	private List<User> users = null;

	public Users() {
	}

	public Users(List<User> users) {
		this.users = users;
	}

	@XmlElementWrapper(name = "users")
	@XmlElement(name = "user")
	public void setUsers(List<User> users) {
		this.users = users;
	}

}
