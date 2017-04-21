package com.example.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class CreateRandomDataUtil {

	private Random random = new Random();

	public String generateRandomChars(String pattern, int length) {
		return random.ints(0, pattern.length())
				.mapToObj(i -> pattern.charAt(i)).limit(length)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
				.toString();
	}

	public Integer generateRandomInteger(Integer integer) {
		return random.ints(integer, 80)
				.findAny().getAsInt();
	}
}
