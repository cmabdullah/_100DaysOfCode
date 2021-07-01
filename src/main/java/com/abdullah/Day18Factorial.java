package com.abdullah;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Day18Factorial {

	public static void main(String[] args) {
		System.out.println(new Day18Factorial().factorial(new BigInteger("10000")));
	}

	public BigInteger factorial(BigInteger n) {
		return Stream.iterate(BigInteger.ONE, fact -> fact.add(BigInteger.ONE))
				.limit(Integer.parseInt(n.toString()))
				.reduce(BigInteger.ONE, BigInteger::multiply);
	}
}
