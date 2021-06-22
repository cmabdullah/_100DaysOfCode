package com.abdullah;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Day11ReflectionApi {
	public static void main(String[] args) {
		X x = new X();
		Y y = new Y();
		try {
			Class classY = Class.forName("com.abdullah.Y"); //dynamically load this class and returns the instance of class
			Method[] methods = classY.getMethods();
			System.out.println(methods[0].getName());
			System.out.println(methods[0]);
			//providing the method name to creates object of desired method
			Method hello = classY.getDeclaredMethod(methods[0].getName(), String.class);
			hello.invoke(y, "Abdullah"); // invokes the method at runtime
			Method privateHello = classY.getDeclaredMethod("privateHello");
			privateHello.setAccessible(true);// give a permission to access private method
			privateHello.invoke(y); //output Private method called
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class Y {

	public Y() {
		System.out.println("Y called");
	}

	public void hello(String name) {
		System.out.println("Hello " + name);
	}

	private void privateHello() {
		System.out.println("Private method called");
	}
}

class X {
	public X() {
		System.out.println("X constructor called");
	}
}
