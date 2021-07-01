package com.abdullah;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * @created 02/07/2021 - 12:24 AM
 * @project _100DaysOfCode
 * @author C M Abdullah Khan
 * REF:
 */
public class Day20ObjectToPrimitive {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3);
		Stream<Integer> integerStream = list.stream();
		//map example
		List<Integer> integerList = integerStream
				.map(n -> n)//To Create Stream
				.mapToDouble(n -> n)// DoubleStream
				.mapToLong(n -> (long) n)//To Create LongStream
				.mapToInt(n -> (int) n)//To Create IntStream
				.mapToObj(n -> n)//To Create Stream
				.collect(Collectors.toList());

		integerList.forEach(System.out::println);
	}
}
