package com.abdullah;

public class Day23Singleton {

	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		System.out.println("number of processors " + singleton.getNumberOfProcessor());
	}
}

class Singleton {

	private final int numberOfProcessor;

	private static class Holder {
		private static final Singleton instance =
				new Singleton(Runtime.getRuntime().availableProcessors());
	}

	private Singleton(int numberOfProcessor) {
		this.numberOfProcessor = numberOfProcessor;
	}

	public static Singleton getInstance() {
		return Holder.instance;
	}

	public int getNumberOfProcessor() {
		return numberOfProcessor;
	}
}
