package com.abdullah;

import java.io.Serializable;

public class Singleton implements Serializable {

	private static class Holder {
		private static final Singleton instance = new Singleton();
	}

	public static Singleton getInstance() {
		return Holder.instance;
	}

	private Singleton() {
		if (getInstance() != null) {
			// exception must be thrown if constructor called by Reflection
			throw new RuntimeException("Can't create instance");
		}
		System.out.println("Creating.....");
	}

	// handle object cloning
	protected Object clone() throws CloneNotSupportedException {
		if (getInstance() != null) {
			throw new CloneNotSupportedException("Can't create instance.");
		}
		return super.clone();
	}

	//Handle deserialization issue
	private Object readResolve() {
		System.out.println("readResolve method called");
		return getInstance();
	}

	@Override
	public String toString() {
		return "Singleton{}";
	}

}
