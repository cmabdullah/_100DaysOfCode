package com.abdullah;

import java.math.BigInteger;
import java.util.stream.Stream;

import static com.abdullah.TailCalls.call;
import static com.abdullah.TailCalls.done;

public class Day19TailRecursive {
	public static BigInteger decrement(final BigInteger number) {
		return number.subtract(BigInteger.ONE);
	}

	public static BigInteger multiply(
			final BigInteger first, final BigInteger second) {
		return first.multiply(second);
	}

	final static BigInteger FORTYK = new BigInteger("40000");

	private static TailCall<BigInteger> factorialTailCallRecursion(final BigInteger factorial, final BigInteger number) {
		if (number.equals(BigInteger.ONE)) return done(factorial);
		else
			return call(() -> factorialTailCallRecursion(multiply(factorial, number), decrement(number)));
	}

	public static BigInteger factorial(final BigInteger number) {
		return factorialTailCallRecursion(BigInteger.ONE, number).invoke();
	}

	public static void main(final String[] args) {
		System.out.println(String.format("%.20s...", factorial(FORTYK)));
	}

}

class TailCalls {
	public static <T> TailCall<T> call(final TailCall<T> nextCall) {
		return nextCall;
	}

	public static <T> TailCall<T> done(final T value) {
		return new TailCall<T>() {
			@Override
			public boolean isComplete() {
				return true;
			}

			@Override
			public T result() {
				return value;
			}

			@Override
			public TailCall<T> apply() {
				throw new Error("not implemented");
			}
		};
	}
}

@FunctionalInterface
interface TailCall<T> {
	TailCall<T> apply();

	default boolean isComplete() {
		return false;
	}

	default T result() {
		throw new Error("not implemented");
	}

	default T invoke() {
		return Stream.iterate(this, TailCall::apply)
				.filter(TailCall::isComplete)
				.findFirst()
				.get()
				.result();
	}
}


