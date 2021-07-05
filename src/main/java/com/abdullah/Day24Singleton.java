package com.abdullah;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Day24Singleton {

	public static void main(String[] args) {
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		Singleton singleton3;
		Singleton singleton4;
		System.out.println("Hashcode of singleton1 : " + singleton1.hashCode());
		System.out.println("Hashcode of singleton2 : " + singleton2.hashCode());

		// corner cases
		//Reflection
		try {
			Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			// creating third instance
			singleton3 = constructor.newInstance();
			System.out.println("Hashcode of singleton3 : " + singleton3.hashCode());
		} catch (RuntimeException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			System.out.println("Constructor through reflection api failed, please call getInstance(), error " + e.getLocalizedMessage());
		}

		// Object Cloning
		try {
			Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			// creating fourth instance
			singleton4 = (Singleton) singleton2.clone();
			System.out.println("Hashcode of singleton4 : " + singleton4.hashCode());
		} catch (RuntimeException | NoSuchMethodException | CloneNotSupportedException e) {
			System.out.println("Object clone failed, please call getInstance(), error " + e.getLocalizedMessage());
		}

		//Serialization
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.home") + "/singleton2.ser"));
			oos.writeObject(singleton2);
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(System.getProperty("user.home") + "/singleton2.ser"));
			Singleton singleton5 = (Singleton) ois.readObject();

			System.out.println("Hashcode of singleton5 : " + singleton5.hashCode());
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Unable to deserialize, please call getInstance(), error " + e.getLocalizedMessage());
		}

		//Multithreaded environment
		ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		Runnable runnable = () -> {
			Singleton singleton = Singleton.getInstance();
			System.out.println("Hashcode of : thread " + Thread.currentThread().getName() + " is " + singleton.hashCode());
		};

		service.submit(runnable);
		service.submit(runnable);
		service.submit(runnable);
		service.submit(runnable);

		try {
			service.shutdown();
			if (service.awaitTermination(100, TimeUnit.MILLISECONDS)) {
				service.shutdownNow();
			}
		} catch (InterruptedException e) {
			service.shutdownNow();
		}
	}
}

