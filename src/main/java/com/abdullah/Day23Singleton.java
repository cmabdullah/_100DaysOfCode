package com.abdullah;

public class Day23Singleton {

	public static void main(String[] args) {
		SingletonV0 singleton = SingletonV0.getInstance();
		System.out.println("number of processors " + singleton.getNumberOfProcessor());
	}
}

class SingletonV0 {

	private final int numberOfProcessor;

	private static class Holder {
		private static final SingletonV0 instance =
				new SingletonV0(Runtime.getRuntime().availableProcessors());
	}

	private SingletonV0(int numberOfProcessor) {
		this.numberOfProcessor = numberOfProcessor;
	}

	public static SingletonV0 getInstance() {
		return Holder.instance;
	}

	public int getNumberOfProcessor() {
		return numberOfProcessor;
	}
}
