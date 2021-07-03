package com.abdullah;

import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class Day22ChainOfResponsibility {

	public static void main(String[] args) {

		var chainOfResponsibility = new MapHandler(Map.of("topping", "cheese",
				"sauce", "marinara", "size", "small"),
				new MapHandler(Map.of("author", "J. R. R. Tolkien"),
						new MapHandler(Map.of("book name", "lord of the rings"), null)));

		chainOfResponsibility.handle("author", System.out::println);
	}
}

abstract class Handler {
	private final Handler next;

	public Handler(Handler next) {
		this.next = next;
	}

	public void handle(String key, Consumer<String> processor) {
		if (Objects.nonNull(next)) {
			next.handle(key, processor);
		}
	}
}

class MapHandler extends Handler {
	private final Map<String, String> map;

	public MapHandler(Map<String, String> map, Handler next) {
		super(next);
		this.map = map;
	}

	@Override
	public void handle(String key, Consumer<String> processor) {
		String item = map.get(key);
		if (Objects.nonNull(item)) {
			processor.accept(item);
		} else {
			super.handle(key, processor);
		}
	}
}