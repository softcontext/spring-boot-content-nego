package com.example.web.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.util.CreateRandomDataUtil;
import com.example.web.model.User;

@Service
public class UserService {

	final String candidateChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final String candidateNum = "0123456789";

	@Autowired
	CreateRandomDataUtil randomData;

	public List<User> findAllUsers() {

		List<User> users = IntStream.rangeClosed(1, 1).mapToObj(i -> new User(i,
				randomData.generateRandomChars(candidateChars, 10), 
				randomData.generateRandomChars(candidateChars, 10)))
				.collect(Collectors.toList());

		return users;
	}
}
