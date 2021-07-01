package com.abdullah;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class _Day13ToMap {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("Germany", "Hungary", "Portugal", "France");
		Function<String, Integer> keyMapperFunction = String::length;
		Function<String, String> valueMapperFunction = (String s) -> s;
		BinaryOperator<String> mergeFunctionBinaryOperator = (String s1, String s2) -> s1 + "," + s2;
		Map<Integer, String> toMapEx = list.stream()
				.collect(
				Collectors
						.toMap(keyMapperFunction, valueMapperFunction, mergeFunctionBinaryOperator));
		System.out.println(toMapEx);
	}
}
